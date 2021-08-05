package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.Balance;
import br.com.alelo.consumer.consumerpat.model.Buy;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value = "/consumerList", method = RequestMethod.GET)
	public ResponseEntity<?> listAllConsumers() {
		return ResponseEntity.ok(consumerService.listAllConsumers());
	}

	@RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
	public ResponseEntity<?> createConsumer(@RequestBody Consumer consumer) {
		return ResponseEntity.ok(consumerService.save(consumer));
	}

	@RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
	public ResponseEntity<?> updateConsumer(@RequestBody Consumer consumer) {
		return ResponseEntity.ok(consumerService.update(consumer));
	}

	@RequestMapping(value = "/setCardBalance", method = RequestMethod.POST)
	public ResponseEntity<?> setBalance(@RequestBody Balance balance) {
		return ResponseEntity.ok(consumerService.setBalance(balance));
	}

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public ResponseEntity<?> buy(@RequestBody Buy buy) {
		return ResponseEntity.ok(consumerService.buy(buy));
	}
}
