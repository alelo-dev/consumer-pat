package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    /**
     * Deixando as regras de negócio sob responsebilidade de um service
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Consumer> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
        repository.save(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if(consumer != null) {
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

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */

        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);

        }else if(establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }

}
