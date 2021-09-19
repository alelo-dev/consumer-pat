package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alelo.consumer.consumerpat.application.consumer.create.CreateConsumerService;
import br.com.alelo.consumer.consumerpat.application.consumer.create.request.CreateConsumerRequest;
import br.com.alelo.consumer.consumerpat.application.consumer.search.SearchConsumerService;
import br.com.alelo.consumer.consumerpat.application.consumer.search.response.ConsumerResponseList;
import br.com.alelo.consumer.consumerpat.application.consumer.update.UpdateConsumerService;
import br.com.alelo.consumer.consumerpat.application.consumer.update.request.UpdateConsumerRequest;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ExtractRepository;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerRepository repository;

	@Autowired
	ExtractRepository extractRepository;

	@Autowired
	private CreateConsumerService createConsumerService;

	@Autowired
	private UpdateConsumerService updateConsumerService;

	@Autowired
	private SearchConsumerService listConsumerService;

	/* Deve listar todos os clientes (cerca de 500) */
	@ResponseBody
	@GetMapping("/consumerList")
	public ResponseEntity<ConsumerResponseList> listAllConsumers(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "500") int size) {
		ConsumerResponseList responseList = listConsumerService.getConsumerList(page, size);

		return ResponseEntity.status(HttpStatus.OK).body(responseList);
	}

	@PostMapping("/createConsumer")
	public ResponseEntity<Void> createConsumer(@RequestBody CreateConsumerRequest request) {

		createConsumerService.createConsumer(request);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping("/updateConsumer")
	public ResponseEntity<Void> updateConsumer(@RequestBody UpdateConsumerRequest request) {

		updateConsumerService.update(request);

		return ResponseEntity.noContent().build();
	}

}
