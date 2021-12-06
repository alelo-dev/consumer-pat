package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    public ResponseEntity<List<ConsumerDTO>> listAllConsumers() {
        return ResponseEntity.ok(service.getAllConsumersList());
    }

    /* Cadastrar novos clientes */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createConsumer(@RequestBody ConsumerDTO dto) {
        service.save(dto);
    }

    // Não deve ser possível alterar o saldo do cartão
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateConsumer(@RequestBody ConsumerDTO dto) {
        service.save(dto);
    }

}
