package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	ConsumerService consumerService;
	
	@Autowired
	CardService cardService;	

	@Autowired
	ExtractService extractService;

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@PostMapping("/setcardbalance")
	public ResponseEntity<Card> setBalance(@RequestBody Card card) {

		Card lCard = cardService.setBalance(card);

		return ResponseEntity.ok(lCard);

	}

	@ResponseBody
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public ResponseEntity<Extract> buy(@RequestBody ExtractDTO extractDTO) {
		
		Extract lExtract = extractService.buy(extractDTO);
		
		return ResponseEntity.ok(lExtract);
		
	}

}
