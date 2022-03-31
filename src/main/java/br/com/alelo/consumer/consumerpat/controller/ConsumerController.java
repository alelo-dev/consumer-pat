package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.interfaces.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping(value = "/consumer-list")
    public ResponseEntity<List<ConsumerDTO>> listAllConsumers() {
        return status(HttpStatus.OK).body(consumerService.getAllConsumersList());
    }

    /* Cadastrar novos clientes */
    @PostMapping(value = "/create-consumer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ConsumerDTO> createConsumer(@RequestBody ConsumerDTO consumer) {
        return status(HttpStatus.OK).body(consumerService.insert(consumer));
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/update-consumer", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ConsumerDTO> updateConsumer(@RequestBody ConsumerDTO consumer) {
        return status(HttpStatus.OK).body(consumerService.update(consumer));
    }
}
