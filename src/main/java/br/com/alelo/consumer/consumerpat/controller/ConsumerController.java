package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.impl.ConsumerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/consumers")
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    ExtractRepository extractRepository;

    /* Deve listar todos os clientes (cerca de 500) */
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public ResponseEntity<List<Consumer>> listAllConsumers() {
        List<Consumer> consumerList= consumerRepository.findAll();
        return new ResponseEntity<>(consumerList, HttpStatus.OK);
    }

    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public ResponseEntity<ConsumerDTO> createConsumer(@RequestBody final ConsumerDTO consumerDTO) {
        return new ResponseEntity<>(consumerService.createConsumer(consumerDTO), HttpStatus.CREATED);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer/{id}", method = RequestMethod.POST)
    public ResponseEntity<ConsumerDTO> updateConsumer(@PathVariable(value = "id") Long id, @RequestBody ConsumerDTO consumerDTO) {
        return new ResponseEntity<>(consumerService.updateConsumer(id, consumerDTO), HttpStatus.OK);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.PUT)
    public ResponseEntity<Card> setBalance(int cardNumber, double value) {
        consumerService.addValue(cardNumber, value);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ResponseEntity<Extract> buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        return new ResponseEntity<>(consumerService.debitValue(establishmentType, establishmentName, cardNumber, productDescription, value), HttpStatus.OK);
    }

}
