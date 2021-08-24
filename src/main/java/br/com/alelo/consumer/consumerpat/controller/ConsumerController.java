package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerBuyDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerSetBalanceDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerRepository repository;

	@Autowired
	ExtractRepository extractRepository;
	
	@Autowired
	ConsumerService consumerService;

	/* Deve listar todos os clientes (cerca de 500) */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Consumer> listAllConsumers() {
		return repository.getAllConsumersList();
	}

	/* Cadastrar novos clientes */
	@PostMapping
	public ResponseEntity<?> createConsumer(@RequestBody ConsumerDto consumerDto) {
		return consumerService.createConsumer(consumerDto);
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping
	public ResponseEntity<?> updateConsumer(@RequestBody ConsumerDto consumerDto) {
		return consumerService.updateConsumer(consumerDto);
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@PostMapping(value = "/balance")
	public ResponseEntity<?> setBalance(@RequestBody ConsumerSetBalanceDto consumerBalanceDto) {
		return consumerService.setBalance(consumerBalanceDto);
	}

	@PostMapping(value = "/buy")
	public ResponseEntity<?> buy(@RequestBody ConsumerBuyDto consumerBuyDto) {
		return consumerService.buy(consumerBuyDto);
	}
}
