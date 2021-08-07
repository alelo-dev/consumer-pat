package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.service.ICardService;

@RestController
@RequestMapping("/card")
public class CardController {
	
	@Autowired
	ICardService service;
	
	@PutMapping(value = "/addbalance")
	public void setCardBalance(@RequestBody CardBalanceDTO cardBalanceDTO) {
		service.setCardBalance(cardBalanceDTO);
	}
	
	
}
