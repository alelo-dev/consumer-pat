package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.command.CreateConsumerCommand;
import br.com.alelo.consumer.consumerpat.command.UpdateConsumerCommand;
import br.com.alelo.consumer.consumerpat.commandhandler.CreateConsumerCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.ListAllConsumerCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.UpdateConsumerCommandHandler;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Log4j2
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired 
    ListAllConsumerCommandHandler listAllConsumerCommandHandler;

    @Autowired
    CreateConsumerCommandHandler createConsumerCommandHandler;

    @Autowired
    UpdateConsumerCommandHandler updateConsumerCommandHandler;

    @Autowired
    ExtractRepository extractRepository;


    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public Page<Consumer> listAllConsumers() {
        log.info("obtendo todos clientes");
        Page<Consumer> allConsumerPageable = listAllConsumerCommandHandler.handle(null);

        return allConsumerPageable;
    }

    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public ResponseEntity<Consumer> createConsumer(@RequestBody CreateConsumerCommand consumerRequest) {

        Consumer consumerResponse = createConsumerCommandHandler.handle(consumerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(consumerResponse);
        
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Consumer> updateConsumer(@PathVariable String id, @RequestBody UpdateConsumerCommand command) {
        command.setId(id);

        Consumer consumerResponse = updateConsumerCommandHandler.handle(command);
        
        return ResponseEntity.status(HttpStatus.OK).body(consumerResponse);
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

    /*
     * Débito de valor no cartão (compra)
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
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
