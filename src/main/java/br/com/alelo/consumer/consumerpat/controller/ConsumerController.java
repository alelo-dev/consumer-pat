package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
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
    ConsumerService service;

    @Autowired
    ExtractRepository extractRepository;

    @Autowired
    public ConsumerController(ConsumerService service, ExtractRepository extractRepository) {
        this.extractRepository = extractRepository;
        this.service = service;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/consumerList")
    public ResponseEntity<List<Consumer>> listAllConsumers() {
        return ResponseEntity.ok(service.getAllConsumersList(5, 1));
    }


    /* Cadastrar novos clientes */
    @PostMapping("/createConsumer")
    public void createConsumer(@RequestBody ConsumerDTO consumer) {
        service.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/updateConsumer")
    public void updateConsumer(@RequestBody ConsumerDTO consumer) {
        service.save(consumer);
    }


}
