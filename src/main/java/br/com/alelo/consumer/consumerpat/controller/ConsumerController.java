package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController extends BaseController {

    private final ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    public ResponseEntity<BaseHttpResponse<List<ConsumerDTO>>> listAllConsumers() {
        return of(service.getAllConsumersList(), HttpStatus.OK);
    }

    /* Cadastrar novos clientes */
    @PostMapping
    public ResponseEntity<BaseHttpResponse<ConsumerIdDTO>> createConsumer(@RequestBody CreateConsumerDTO dto) {
        return of(service.create(dto), HttpStatus.CREATED);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PatchMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateConsumer(@RequestBody UpdateConsumerDTO dto) {
        service.update(dto);
    }

}
