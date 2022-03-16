package br.com.alelo.consumer.consumerpat.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;


@Controller
@RequestMapping("/consumers")
public class ConsumersController {

    @Autowired
    ConsumerRepository repository;

    private final ConsumerService service;
    
    @Autowired
    public ConsumersController(ConsumerService service) {
        this.service = service;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return this.service.findAll();
    }

    /* Cadastrar novos clientes */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createConsumer(@RequestBody Consumer consumer) {
        final var newConsumer = this.service.createAddress(consumer);

        final var location = URI.create("/consumers/".concat(newConsumer.getId().toString()));
        return ResponseEntity.created(location).build();
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer, @PathVariable("Id") Integer id) {
        final var updatedConsumer = this.service.updateAddress(consumer, id);

        return ResponseEntity.ok(updatedConsumer);
    }

}
