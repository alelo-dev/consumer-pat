package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(ConsumerController.BASE_PATH)
public class ConsumerController {

	/* URL base para Consumer endpoints */
	public static final String BASE_PATH = "/api/v1";

	/* URL relativa para endpoints de todas as operações */
	public static final String CONSUMERS = "/consumers";

	private final ConsumerService consumerService;

	@Autowired
	public ConsumerController(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}

	@GetMapping(value = CONSUMERS)
	@ApiOperation(value = "Get all consumers")
	public ResponseEntity<List<Consumer>> getAll(@RequestParam(defaultValue = "0") int offset,
												 @RequestParam(defaultValue = "20") int size) {
		
		final List<Consumer> allConsumers = consumerService.getAllPaginated(offset, size);
		
		if (allConsumers == null || allConsumers.isEmpty()) {
			return ResponseEntity.noContent().build(); // adequar ao padrao existente na empresa, tratar novos tipos de mensagens retornadas ao cliente, se mais especificidade é necessario...
		}
		
		return new ResponseEntity<>(allConsumers, HttpStatus.OK);
	}

	@GetMapping(value = CONSUMERS + "/{id}")
	@ApiOperation(value = "Get one consumer")
	public ResponseEntity<Consumer> findOne(@PathVariable("id") long id) {
		final Consumer consumer;
		
		try {
			consumer = consumerService.findOne(id);			
		} catch(Exception e) {		
			return ResponseEntity.noContent().build(); // adequar ao padrao existente na empresa, tratar novos tipos de mensagens retornadas ao cliente 
		}
		
		return new ResponseEntity<>(consumer, HttpStatus.OK);
	}
	
	@PostMapping(value = CONSUMERS)
	@ApiOperation(value = "Create new consumer")
	public ResponseEntity<Consumer> createOne(@RequestBody Consumer consumer) {
		final Consumer newConsumer;
		
		try {
			newConsumer = consumerService.createOne(consumer);
		} catch(Exception e) {	
			return ResponseEntity.badRequest().build(); // adequar ao padrao existente na empresa, tratar novos tipos de mensagens retornadas ao cliente 
		}
		
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path(CONSUMERS + "/{id}")
				.buildAndExpand(newConsumer.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(newConsumer);
	}

	@PutMapping(value = CONSUMERS)
	@ApiOperation(value = "Update a consumer")
	public ResponseEntity<Consumer> updateOne(@RequestBody Consumer consumer) {
		final Consumer updatedConsumer;
		
		try {
			updatedConsumer = consumerService.updateOne(consumer);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build(); // adequar ao padrao existente na empresa, tratar novos tipos de mensagens retornadas ao cliente 
		}
			
		return ResponseEntity.ok().body(updatedConsumer);
	}

}