package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	ConsumerService consumerService;

	/* Deve listar todos os clientes (cerca de 500) */
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/consumerList", method = RequestMethod.GET)
	public List<Consumer> listAllConsumers() {
		return consumerService.listAllConsumers();
	}

	/* Cadastrar novos clientes */
	@RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
	public ResponseEntity<Object> createConsumer(@RequestBody Consumer consumer) {
		try {
			String response = consumerService.createConsumer(consumer);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao realizar requisicao", HttpStatus.BAD_REQUEST);
		}
	}

	// Não deve ser possível alterar o saldo do cartão
	@RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateConsumer(@RequestBody Consumer consumer) throws Exception {
		try {
			String response = consumerService.updateConsumer(consumer);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao realizar requisicao", HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa idenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
	public ResponseEntity<Object> setBalance(@RequestParam int cardNumber, @RequestParam double value) {
		try {
			String response = consumerService.setBalance(cardNumber, value);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao realizar requisicao", HttpStatus.BAD_REQUEST);
		}
	}

}
