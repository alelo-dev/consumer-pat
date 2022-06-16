package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	CardRepository cardRepository;

	@GetMapping
	public List<Card> list() {
		return cardRepository.findAll();
	}

	@GetMapping("/{cardNumber}")
	public Card find(@PathVariable Integer cardNumber) {
		return cardRepository.findByNumber(cardNumber);
	}

	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
	 * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
	 * usar o número do cartão(cardNumber) fornecido.
	 */
	@GetMapping(value = "/setcardbalance")
	public ResponseEntity<Card> setBalance(int cardNumber, double value) {
		Card card = cardRepository.findByNumber(cardNumber);
		if (card != null) {
			card.setBalance(value);
			card = cardRepository.save(card);
			return ResponseEntity.ok().body(card);
		}
		return ResponseEntity.notFound().build();
	}
}
