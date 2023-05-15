package br.com.alelo.consumer.consumerpat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerRepository repository;
    private final ExtractRepository extractRepository;

    @Autowired
    public ConsumerController(ConsumerRepository repository, ExtractRepository extractRepository) {
        this.repository = repository;
        this.extractRepository = extractRepository;
    }

    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        log.info("obtendo todos clientes");
        var consumers = repository.getAllConsumersList();

        return consumers;
    }

    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                repository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                repository.save(consumer);
            }
        }
    }

    /*
     * Débito de valor no cartão (compra)
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */
    @PostMapping("/buy")
    public void buy(
            @RequestParam int establishmentType,
            @RequestParam String establishmentName,
            @RequestParam int cardNumber,
            @RequestParam String productDescription,
            @RequestParam double value) {
        Consumer consumer = null;
        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.

        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
        *
        * Tipos dos estabelcimentos:
        *    1) Alimentação (Food)
        *    2) Farmácia (DrugStore)
        *    3) Posto de combustivel (Fuel)
        */

        if (establishmentType == 1) {
            // Para compras no cartão de alimentação, o cliente recebe um desconto de 10%
            BigDecimal cashback = BigDecimal.valueOf((value / 100) * 10);
            value = value.subtract(cashback);

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance().subtract(BigDecimal.valueOf(value)));
            repository.save(consumer);

        } else if (establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance().subtract(BigDecimal.valueOf(value)));
            repository.save(consumer);

        } else if (establishmentType == 3) {
            // Nas compras com o cartão de combustível, existe um acréscimo de 35%
            BigDecimal tax = BigDecimal.valueOf((value / 100) * 35);
            value = value.add(tax);

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance().subtract(BigDecimal.valueOf(value)));
            repository.save(consumer);
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, BigDecimal.valueOf(value));
        extractRepository.save(extract);
    }
}