package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.respository.filter.BuyFilter;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerRepository repository;

	@Autowired
	ConsumerService consumerService;

	@Autowired
	ExtractRepository extractRepository;

	/* Deve listar todos os clientes (cerca de 500) */
	@GetMapping("/consumerList")
	public @ResponseBody ResponseEntity<Object> listAllConsumers() {

		List<Consumer> listConsumers = repository.getAllConsumersList();
		return new ResponseEntity<Object>(listConsumers, HttpStatus.OK);
	}

	/* Cadastrar novos clientes */
	@PostMapping("/createConsumer")
	public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer, HttpServletRequest response) {

		Consumer consumerSaved = consumerService.salvar(consumer);
		return ResponseEntity.status(HttpStatus.CREATED).body(consumerSaved);
	}

	// Não deve ser possível alterar o saldo do cartão
	@PutMapping("/updateConsumer")
	public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {

		Consumer consumerSaved = consumerService.atualizar(consumer);
		return ResponseEntity.ok(consumerSaved);
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
	public void setBalance(int cardNumber, double value) {
		Consumer consumer = null;
		consumer = repository.findByDrugstoreNumber(cardNumber);

		if (consumer != null) {
			// é cartão de farmácia
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
			repository.save(consumer);
		} else {
			consumer = repository.findByFoodCardNumber(cardNumber);
			if (consumer != null) {
				// é cartão de refeição
				consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
				repository.save(consumer);
			} else {
				// É cartão de combustivel
				consumer = repository.findByFuelCardNumber(cardNumber);
				consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
				repository.save(consumer);
			}
		}
	}

	@PostMapping("/buy")
	public ResponseEntity<Consumer> buy(@RequestBody BuyFilter buyFilter) {
		Consumer consumerSaved = consumerService.realizarCompra(buyFilter);
		return ResponseEntity.ok(consumerSaved);
	}

}
