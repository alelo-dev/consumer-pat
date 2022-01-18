package br.com.alelo.consumer.consumerpat.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
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
	public List<Consumer> listAllConsumers() {
		return consumerService.getAllConsumersList();
	}

	/* Cadastrar novos clientes */
	// @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
		consumer.setResidencePhoneNumber("3");
		Consumer lConsumerReturn = consumerService.save(consumer);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(lConsumerReturn.getId()).toUri();

		return ResponseEntity.created(uri).body(lConsumerReturn);
	}

	// Não deve ser possível alterar o saldo do cartão
	// @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
	@PutMapping("/{id}")
	public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer, @PathVariable Integer id) {

		Consumer lConsumer = consumerService.updateConsumer(id, consumer);

		return ResponseEntity.ok(lConsumer);
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	// @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
	@PostMapping("/setcardbalance")
	public ResponseEntity<Consumer> setBalance(@RequestBody Card card) {

		Consumer lConsumer = consumerService.setBalance(card);

		return ResponseEntity.ok(lConsumer);

	}

	@ResponseBody
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public ResponseEntity<Extract> buy(@RequestBody ExtractDTO extractDTO) {
		
		Extract lExtract = extractService.buy(extractDTO);
		
		return ResponseEntity.ok(lExtract);
		
	}

}
