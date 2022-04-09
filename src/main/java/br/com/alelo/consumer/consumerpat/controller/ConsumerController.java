package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.impl.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.impl.ExtractService;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;

	@Autowired
	ExtractService extractService;

	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/consumerList", method = RequestMethod.GET)
	public List<Consumer> listAllConsumers() {
		return consumerService.getAllConsumersList();
	}

	@RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
	public void createConsumer(@RequestBody Consumer consumer) {
		consumerService.createConsumer(consumer);
	}

	@RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
	public void updateConsumer(@RequestBody Consumer consumer) {
		consumerService.updateConsumer(consumer);
	}

	@RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
	public void setBalance(int cardNumber, double value) {
		consumerService.setBalance(cardNumber, value);
	}

	@ResponseBody
	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
		consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
	}

}
