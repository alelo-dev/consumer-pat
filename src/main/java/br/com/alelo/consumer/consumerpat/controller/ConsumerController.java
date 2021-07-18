package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/consumer")
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
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        return new ResponseEntity<>(service.save(consumer), HttpStatus.CREATED);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/updateConsumer")
    public ResponseEntity<Void> updateConsumer(@RequestBody Consumer consumer) {
        service.update(consumer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @GetMapping(value = "/setcardbalance")
    public void setBalance(int cardNumber, double value) {
        service.cardBalance(cardNumber, value);
    }

    @ResponseBody
    @GetMapping(value = "/buy")
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        service.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
