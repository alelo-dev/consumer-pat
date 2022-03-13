package br.com.alelo.consumer.consumerpat.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.CardValueTransactionDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.model.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@RestController
@RequestMapping("/v1/consumer")
public class ConsumerController {

	private ConsumerService consumerService;

	private CardService cardService;

	@Autowired
	public ConsumerController(ConsumerService consumerService, CardService cardService) {
		this.consumerService = consumerService;
		this.cardService = cardService;
	}

	@GetMapping("/consumerList")
	public ResponseEntity<Page<Consumer>> listAllConsumers(
			@RequestParam(value = "page", required = true, defaultValue = "0") int page,
			@RequestParam(value = "size", required = true, defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Consumer> findAllConsumers = consumerService.findAllConsumers(pageable);
		return ResponseEntity.ok(findAllConsumers);
	}

	@PostMapping("/createConsumer")
	public ResponseEntity<Consumer> createConsumer(@RequestBody @Valid ConsumerDTO consumerDTO) {
		Consumer consumer = consumerService.convertAndSaveConsumer(consumerDTO);
		return ResponseEntity.ok(consumer);
	}

	@PutMapping("/updateConsumer/{consumerId}")
	public ResponseEntity<Consumer> updateConsumer(@RequestBody @Valid ConsumerDTO consumerDTO,
			@PathVariable(name = "consumerId", required = true) Long consumerId) {
		Consumer consumer = consumerService.updateConsumer(consumerDTO, consumerId);
		return ResponseEntity.ok(consumer);
	}

	@PostMapping("/setcardbalance")
	public ResponseEntity<CardDTO> setBalance(@RequestBody @Valid CardValueTransactionDTO creditCardDTO) {
		CardDTO card = cardService.setBalance(creditCardDTO);
		return ResponseEntity.ok(card);
	}

	@PostMapping("buy")
	public ResponseEntity<ExtractDTO> buy(@RequestBody RequestBuyDTO requestBuyDTO) {
		ExtractDTO extract = cardService.buyProductWithCard(requestBuyDTO);
		return ResponseEntity.ok(extract);

	}

}
