package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.application.card.balance.CardBalanceService;
import br.com.alelo.consumer.consumerpat.application.card.balance.request.CardBalanceRequest;
import br.com.alelo.consumer.consumerpat.application.card.balance.response.CardBalanceResponse;
import br.com.alelo.consumer.consumerpat.application.card.buy.CardBuyService;
import br.com.alelo.consumer.consumerpat.application.card.buy.request.CardBuyRequest;
import br.com.alelo.consumer.consumerpat.application.card.buy.response.TransactionResponse;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	private CardBalanceService cardBalanceService;

	@Autowired
	private CardBuyService cardBuyService;

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@PutMapping("/setcardbalance")
	public ResponseEntity<CardBalanceResponse> setBalance(@RequestBody CardBalanceRequest request) {

		CardBalanceResponse response = cardBalanceService.setBalance(request.getCardNumber(), request.getValue());
		return ResponseEntity.ok().body(response);
	}

	@PostMapping(value = "/buy")
	public ResponseEntity<TransactionResponse> buy(@RequestBody CardBuyRequest request) {
		TransactionResponse buy = cardBuyService.buy(request);
		return ResponseEntity.ok(buy);
	}

}
