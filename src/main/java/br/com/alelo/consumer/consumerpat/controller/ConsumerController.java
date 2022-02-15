package br.com.alelo.consumer.consumerpat.controller;

import java.net.URI;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;
	
	@Autowired
	ExtractService extractService;

	/* Deve listar todos os clientes (cerca de 500) */
	@GetMapping
	public Page<Consumer> listAllConsumers(Pageable pageable) {
		if(Objects.isNull(pageable)) {
			pageable = Pageable.unpaged();
		}
		return consumerService.getAllConsumersListWhithPagination(pageable);
	}

	@PostMapping
	public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
		consumer.setResidencePhoneNumber("3");
		Consumer lConsumerReturn = consumerService.save(consumer);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(lConsumerReturn.getId()).toUri();

		return ResponseEntity.created(uri).body(lConsumerReturn);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer, @PathVariable Integer id) {

		Consumer lConsumer = consumerService.updateConsumer(id, consumer);

		return ResponseEntity.ok(lConsumer);
	}
}
