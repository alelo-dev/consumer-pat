package br.com.alelo.consumer.consumerpat.controller;

import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.ResponseHandler;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.strategy.ConsumerContext;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerRepository repository;

	@Autowired
	ExtractRepository extractRepository;

	@Autowired
	private ModelMapper mapper;

	/* Deve listar todos os clientes (cerca de 500) */
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(value = "/consumerList")
	public ResponseEntity<Object> listAllConsumers() {
		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK,
				repository.getAllConsumersList());
	}

	/* Cadastrar novos clientes */
	@ResponseBody
	@PostMapping(value = "/createConsumer")
	public ResponseEntity<Object> createConsumer(@RequestBody ConsumerDTO consumerDTO) {
		String message = "Already Created";
		try {
			Consumer consumer = convertToEntity(consumerDTO);
			ConsumerContext consumerContext = new ConsumerContext();
			consumerContext.setStrategy(repository, consumer);
			consumer = consumerContext.create(repository, consumer);

			if (consumer == null) {
				return ResponseHandler.generateResponse(message, HttpStatus.ALREADY_REPORTED, consumer);
			}
			return ResponseHandler.generateResponse(message, HttpStatus.OK, consumer);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	// Não deve ser possível alterar o saldo do cartão
	@ResponseBody
	@PostMapping(value = "/updateConsumer")
	public ResponseEntity<Object> updateConsumer(@RequestBody ConsumerDTO consumerDTO) {
		try {
			Consumer consumer = convertToEntity(consumerDTO);
			ConsumerContext consumerContext = new ConsumerContext();
			consumerContext.setStrategy(repository, consumer);
			consumer = consumerContext.update(repository, consumer);
			return ResponseHandler.generateResponse("Updated", HttpStatus.OK, consumer);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
		}
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@GetMapping(value = "/setcardbalance")
	public ResponseEntity<Object> setBalance(Long cardNumber, double value) {
		try {
			ConsumerContext consumerContext = new ConsumerContext();
			consumerContext.setStrategy(repository, cardNumber);
			Consumer consumer = consumerContext.setCardBalance(repository, value);
			return ResponseHandler.generateResponse("Balance updated", HttpStatus.OK, consumer);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
		}

	}

	@ResponseBody
	@GetMapping(value = "/buy")
	public ResponseEntity<Object> buy(int establishmentType, String establishmentName, Long cardNumber,
			String productDescription, double value) {
		Consumer consumer = null;
		/*
		 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
		 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
		 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
		 * cartão e alimentação
		 *
		 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */

		try {
			ConsumerContext consumerContext = new ConsumerContext();
			consumerContext.setStrategy(repository, cardNumber);
			Optional<Consumer> consumerOp = consumerContext.buy(repository, establishmentType, value);

			// TODO: improve for many buys
			Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
			extractRepository.save(extract);

			if (consumerOp.isPresent()) {
				return ResponseHandler.generateResponse("Buy...", HttpStatus.OK, consumerOp.get());
			} else {
				return ResponseHandler.generateResponse("Not Buyed...", HttpStatus.OK, consumer);
			}

		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
		}

	}

	private Consumer convertToEntity(ConsumerDTO consumerDTO) {
		Consumer consumer = mapper.map(consumerDTO, Consumer.class);
		return consumer;
	}

}
