package br.com.alelo.consumer.consumerpat.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.api.dto.input.ConsumerInput;
import br.com.alelo.consumer.consumerpat.api.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerRegisterService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/consumers")
public class ConsumerController {

    @Autowired
    ConsumerMapper mapper;

    @Autowired
    ConsumerRegisterService consumerService;

    @GetMapping
    private List<Consumer> getAllOrWithPagination(@PageableDefault(size = 10) Pageable pageable) {
        return consumerService.getConsumers(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ConsumerInput input) {
        Consumer consumer = mapper.toDomainObject(input);
        consumerService.save(consumer);
    }

    @PutMapping("/{consumerId}")
    public void update(@PathVariable Long consumerId, @RequestBody ConsumerInput input) {
        Consumer existentConsumer = consumerService.findById(consumerId);
        Consumer consumerUpdated = mapper.copyToDomainObject(input, existentConsumer);
        consumerService.save(consumerUpdated);
    }
}
