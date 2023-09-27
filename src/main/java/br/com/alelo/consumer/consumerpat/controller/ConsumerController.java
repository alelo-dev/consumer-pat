package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.command.CreateConsumerCommand;
import br.com.alelo.consumer.consumerpat.command.PurchaseOperationCommand;
import br.com.alelo.consumer.consumerpat.command.UpdateCardBalanceCommand;
import br.com.alelo.consumer.consumerpat.command.UpdateConsumerCommand;
import br.com.alelo.consumer.consumerpat.commandhandler.CreateConsumerCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.ListAllConsumerCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.PurchaseOperationCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.UpdateCardBalanceCommandHandler;
import br.com.alelo.consumer.consumerpat.commandhandler.UpdateConsumerCommandHandler;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    UpdateCardBalanceCommandHandler updateCardBalanceCommandHandler;

    @Autowired
    PurchaseOperationCommandHandler purchaseOperationCommandHandler;

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
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
    public ResponseEntity<Consumer> setBalance(@RequestBody UpdateCardBalanceCommand command) {

        Consumer consumerResponse = updateCardBalanceCommandHandler.handle(command);        

        return ResponseEntity.status(HttpStatus.OK).body(consumerResponse);
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
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<Consumer> buy(@RequestBody PurchaseOperationCommand command) {

        purchaseOperationCommandHandler.handle(command);

        return ResponseEntity.status(HttpStatus.OK).body(null);

        /* O valor só podem ser debitado do catão com o tipo correspondente ao tipo do estabelecimento da compra.

        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação (food) então o valor só pode ser debitado do cartão alimentação
        *
        * Tipos dos estabelcimentos:
        *    1) Alimentação (Food)
        *    2) Farmácia (DrugStore)
        *    3) Posto de combustivel (Fuel)
        */
    }

}
