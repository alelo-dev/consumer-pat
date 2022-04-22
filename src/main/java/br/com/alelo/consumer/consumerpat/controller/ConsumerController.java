package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerPatService;
import br.com.alelo.consumer.consumerpat.util.Buy;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerPatService service;

	/* Deve listar todos os clientes (cerca de 500) */
	@RequestMapping("/consumerList")
	public ResponseEntity<List<Consumer>> listAllConsumers() {
		return ResponseEntity.ok(service.listAllConsumers());
	}

	/* Cadastrar novos clientes */
	@PostMapping("/createConsumer")
	public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {

		if (service.createConsumer(consumer) != null) {
			return new ResponseEntity<Consumer>(consumer, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Consumer>(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	// Não deve ser possível alterar o saldo do cartão
	@PatchMapping("/updateConsumer")
	public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {

		Optional<Consumer> currentConsumer = service.updateConsumer(consumer);
		if (currentConsumer.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<Consumer>(currentConsumer.get(), HttpStatus.OK);
	}

	@PutMapping(value = "/setcardbalance/{cardNumber}/{value}")
	public ResponseEntity<Consumer> setBalance(@PathVariable("cardNumber") int cardNumber,
			@PathVariable("value") double value) {
		return new ResponseEntity<Consumer>(service.setBalance(cardNumber, value), HttpStatus.OK);

	}

	@PostMapping(value = "/buy")
	public ResponseEntity<Consumer> buy(@RequestBody Buy buy) {

		if (service.buy(buy)) {
			return new ResponseEntity<Consumer>(HttpStatus.CREATED);
		}

		return new ResponseEntity<Consumer>(HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
