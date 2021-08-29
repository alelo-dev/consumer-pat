package br.com.alelo.consumer.consumerpat.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.domain.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

	private final CardService service;
	
	 @PatchMapping("/{cardNumber}/balance")
	 public void setBalance(@PathVariable("cardNumber") String cardNumber, @RequestBody BigDecimal value) {
	     service.setBalance(cardNumber, value);
	 }

	 @PatchMapping("/{cardNumber}/buy")
	 public void buy(@PathVariable String cardNumber, @RequestBody BuyDTO buyDTO) {
	 	service.buy(cardNumber, buyDTO);
	 }
	
}
