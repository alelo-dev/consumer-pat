package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.domain.payload.ConsumerPayload;
import br.com.alelo.consumer.consumerpat.domain.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService service;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    public Page<ConsumerResponse> listAllConsumers(Pageable page) {
        return service.listAllConsumers(page);
    }


    /* Cadastrar novos clientes */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createConsumer(@RequestBody ConsumerPayload consumer) {
        service.createConsumer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateConsumer(@PathVariable("id") Integer id, @RequestBody ConsumerPayload consumer) {
        service.updateConsumer(id, consumer);
    }

}
