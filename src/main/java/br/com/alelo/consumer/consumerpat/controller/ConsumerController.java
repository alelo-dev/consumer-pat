package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.GetConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ExtractService extractService;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<GetConsumerDTO> listAllConsumers() {
        return consumerService.getAllConsumers();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public ResponseEntity<?> createConsumer(@RequestBody CreateConsumerDTO consumer) {
        consumerService.createConsumer(consumer);
        return ResponseEntity.ok().build();
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateConsumer(@RequestBody UpdateConsumerDTO consumer, @PathVariable int id) {
        consumerService.updateConsumer(consumer, id);
        return ResponseEntity.ok().build();
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public ResponseEntity<?> setBalance(int cardNumber, double value) {
        consumerService.balance(cardNumber, value);
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ResponseEntity<?> buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        extractService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
        return ResponseEntity.noContent().build();
    }

}
