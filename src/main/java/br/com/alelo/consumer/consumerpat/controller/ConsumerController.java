package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.business.ConsumerBusinessInt;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerBusinessInt consumerBusinessint;

	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/consumerList", method = RequestMethod.GET)
	public List<Consumer> listAllConsumers() {
		return consumerBusinessint.listAllConsumers();
	}

	@RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
	public ResponseEntity<String> createConsumer(@RequestBody Consumer consumer) throws BusinessException {

		consumer = consumerBusinessint.createConsumer(consumer);
		return new ResponseEntity<>("Consumidor " + consumer.getName() + " criado com sucesso !", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
	public ResponseEntity<String> updateConsumer(@RequestBody Consumer consumer) throws BusinessException {

		consumer = consumerBusinessint.updateConsumer(consumer);
		return new ResponseEntity<>("Consumidor " + consumer.getName() + " alterado com sucesso !", HttpStatus.OK);
	}

	@RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
	public ResponseEntity<String> setBalance(long cardNumber, double value) throws BusinessException {

		consumerBusinessint.addBalance(cardNumber, value);
		return new ResponseEntity<>("Crédito efetuado com sucesso !", HttpStatus.OK);
	}

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public ResponseEntity<String> buy(int establishmentType, String establishmentName, int cardNumber, String productDescription,
			double value) throws BusinessException {

		consumerBusinessint.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
		return new ResponseEntity<>(
				"Compra com cartão " + cardNumber + " do produto " + productDescription + " efetuada com sucesso !",
				HttpStatus.CREATED);
	}

}