package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    //TODO return DTO
    //TODO fix path
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<ConsumerDTO> listAllConsumers() {
        return consumerService.all().stream()
                .map(ConsumerDTO::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ConsumerDTO> getById(@PathVariable int id) {
        return consumerService.withId(id)
                .map(ConsumerDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //TODO POST /customers/
    //TODO move logict to CustomerService
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public <T> ResponseEntity<T> createConsumer(@RequestBody ConsumerDTO consumer) {
        var created = consumerService.persist(consumer);
        var createdCustomerLink = String.format("/customer/%s", created.getId());
        return ResponseEntity.created(URI.create(createdCustomerLink)).build();
    }

    @PutMapping("{consumerId}")
    public <T> ResponseEntity<T> update(@PathVariable("consumerId") Integer id, @RequestBody ConsumerDTO consumer) {
        var found = consumerService.merge(id, consumer);
        return found.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }

}
