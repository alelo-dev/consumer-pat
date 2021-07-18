package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.*;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ConsumerService service;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @GetMapping(value = "/consumerList")
    public ResponseEntity<List<Consumer>> listAllConsumers() {
        return ResponseEntity.ok(repository.findAll());
    }


    /* Cadastrar novos clientes */
    @PostMapping(value = "/createConsumer")
    public ResponseEntity<Consumer> createConsumer(@RequestBody @Validated ConsumerPostDTO consumerPostDTO) {
        return new ResponseEntity<>(this.service.save(consumerPostDTO), HttpStatus.CREATED);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/updateConsumer")
    public ResponseEntity<Void> updateConsumer(@RequestBody @NonNull ConsumerPutDTO consumerPutDTO) {
        this.service.update(consumerPutDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */

    @ResponseBody
    @PostMapping(value = "/setcardbalance")
    public ResponseEntity<Void> setBalance(@RequestBody CardBalance cardBalance) {
        this.service.cardBalance(cardBalance);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseBody
    @PostMapping(value = "/buy")
    public ResponseEntity<Void> buy(@RequestBody ExtractDTO extractDTO) {
        this.service.buy(extractDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
