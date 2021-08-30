package br.com.alelo.consumer.consumerpat.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.domain.payload.BuyPayload;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

	private final CardService service;
	
	 @PatchMapping("/{cardNumber}/balance")
	 @ResponseStatus(code = HttpStatus.ACCEPTED)
	 public void setBalance(@PathVariable("cardNumber") String cardNumber, @RequestBody BigDecimal value) {
	     service.setBalance(cardNumber, value);
	 }

	 @PatchMapping("/{cardNumber}/buy")
	 @ResponseStatus(code = HttpStatus.ACCEPTED)
	 public void buy(@PathVariable String cardNumber, @RequestBody BuyPayload buyDTO) {
	 	service.buy(cardNumber, buyDTO);
	 }
	
}
