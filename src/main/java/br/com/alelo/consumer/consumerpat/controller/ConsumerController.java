package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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

import br.com.alelo.consumer.consumerpat.payload.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.payload.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.payload.ConsumerUpdateRequest;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;

	/* Deve listar todos os clientes (cerca de 500) */
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public PageImpl<ConsumerResponse> listAllConsumers(final Pageable pageable) {
		return this.consumerService.findAll(pageable);
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ConsumerResponse findById(@PathVariable final Long id) {
		return this.consumerService.findConsumerDTOById(id);
	}

	/* Cadastrar novos clientes */
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void createConsumer(@RequestBody final ConsumerRequest consumerRequest) {
		this.consumerService.save(consumerRequest);
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateConsumer(@PathVariable final Long id, @RequestBody final ConsumerUpdateRequest consumerUpdateRequest) {
		this.consumerService.update(id, consumerUpdateRequest);
	}

}