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

    /**
     * Retorna a lista de até 500 consumidores.
     *
     * @return Lista de objetos ConsumerDTO
     */
    @GetMapping("/consumers")
    public ResponseEntity<List<ConsumerDTO>> listAllConsumers() {
        List<ConsumerDTO> consumers = service.getAllConsumersList();
        return ResponseEntity.ok(consumers);
    }

    /* Cadastrar novos clientes */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createConsumer(@RequestBody ConsumerDTO dto) {
        service.save(dto);
    }


    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateConsumer(@RequestBody ConsumerDTO dto) {
        service.save(dto);
    }

}