package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;

	@Autowired
	CardService cardService;
	
	@ResponseBody
	@GetMapping
	public ResponseEntity<Page<Consumer>> listAllConsumers(//
			@RequestParam("page")
			int page,//
			@RequestParam("size")
			int size) {
		Page<Consumer> consumers = consumerService.findAll(page, size);
		return ResponseEntity.ok(consumers);
	}

	@PostMapping
	public ResponseEntity<Void> createConsumer(@RequestBody ConsumerDto consumerDto) {
		consumerService.save(consumerDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateConsumer(@PathVariable("id") Long id, @RequestBody ConsumerDto consumerDto) {
		consumerService.update(id, consumerDto);
		return ResponseEntity.ok().build();
	}


	@PatchMapping("/assign/{cardNumber}")
	public ResponseEntity<Void>  setBalance(//
			@PathVariable("cardNumber") Long cardNumber, //

			@RequestParam("value") double value) {
		cardService.assign(cardNumber, value);
		return ResponseEntity.ok().build();
	}

	@ResponseBody
	@PatchMapping("/buy/{cardNumber}")
	public ResponseEntity<Void>  buy(//
			@PathVariable("cardNumber") Long cardNumber,
			@RequestBody BuyDto buyDto) {
		cardService.buy(cardNumber, buyDto);
		return ResponseEntity.ok().build();
	}

}
