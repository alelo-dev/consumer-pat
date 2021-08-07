package br.com.alelo.consumer.consumerpat.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alelo.consumer.consumerpat.dto.ConsumerCreateDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	
	@Autowired
	IConsumerService service;

    @GetMapping
    public Page<ConsumerDTO> pageConsumers(
    		@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage) {
        return service.pageConsumers(page, linesPerPage);
    }

    @PostMapping
    public ResponseEntity<Void> createConsumer(@RequestBody ConsumerCreateDTO consumerCreateDTO) {
        Consumer consumer = service.create(consumerCreateDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consumer.getId()).toUri();
		return ResponseEntity.created(uri).build();
    }

    
    @PutMapping
    public void updateConsumer(@RequestBody ConsumerDTO consumerDTO) {
        service.update(consumerDTO);
    }
    
    @GetMapping(value = "/{id}")
    public ConsumerDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
