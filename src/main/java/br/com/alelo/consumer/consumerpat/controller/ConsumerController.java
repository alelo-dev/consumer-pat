package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @RequestMapping("/consumerList")
    public ResponseEntity<List<Consumer>> listAllConsumers() {
        List<Consumer> consumer = service.listAllConsumers();
        return ResponseEntity.ok(consumer);
    }

    /* Cadastrar novos clientes */
    @PostMapping("/createConsumer")
    public ResponseEntity<Consumer> createConsumer(@Valid  @RequestBody Consumer consumer) {
        consumer = service.saveConsumers(consumer);
        return ResponseEntity.status(HttpStatus.CREATED).body(consumer);
    }

    // Deve atualizar o saldo dos cartoes
    @GetMapping("/setcardbalance/cardNumber/{cardNumber}/value/{value}/brands/{brands}")
    public ResponseEntity<Consumer> setBalance(@Valid
                                               @PathVariable Long cardNumber,
                                               @PathVariable Long value,
                                               @PathVariable Integer brands) {
        Consumer consumer = service.setBalance(cardNumber, value, brands);

        return ResponseEntity.ok().body(consumer);
    }


    @GetMapping("/buy/establishmentType/{establishmentType}/establishmentName/{establishmentName}/cardNumber/{cardNumber}/productDescription/{productDescription}/value/{value}")
    public ResponseEntity<Consumer> buy(@Valid
                                        @PathVariable Integer establishmentType,
                                        @PathVariable String establishmentName,
                                        @PathVariable Long cardNumber,
                                        @PathVariable String productDescription,
                                        @PathVariable Long value) {

        Consumer consumer = service.buy(establishmentType,
                                        establishmentName,
                                        cardNumber,
                                        productDescription,
                                        value);

        return ResponseEntity.ok(consumer);

    }

}
