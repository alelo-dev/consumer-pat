package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {


    private ConsumerService service;

    @Autowired
    public ConsumerController(ConsumerService service){
        this.service = service;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping("/consumerList")
    public ResponseEntity<Page<Consumer>> listAllConsumers(@RequestParam("page") int page,
                                                           @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllConsumersList(pageable));
    }



    /* Cadastrar novos clientes */
    @PostMapping("/createConsumer")
    public ResponseEntity<Consumer> createConsumer(@RequestBody ConsumerDTO consumer) {
        return ResponseEntity.ok(service.save(consumer));
    }

    // Não deve ser possível alterar o saldo do cartão
    // A responsabilidade de atualização de saldo do cartão foi para o EndPoint de cartões.
    @PutMapping("/updateConsumer")
    public void updateConsumer(@RequestBody ConsumerDTO consumer) {
        service.save(consumer);
    }

}
