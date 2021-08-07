package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.service.ICardService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/card")
@Api(value = "Cartões")
public class CardController {
	
	@Autowired
	ICardService service;
	
	@Operation(summary = "Creditar recursos", description = "Utilize este serviço para creditar novos recursos a um determinado cartão.")
	@PutMapping(value = "/addbalance")
	public void setCardBalance(@RequestBody CardBalanceDTO cardBalanceDTO) {
		service.setCardBalance(cardBalanceDTO);
	}
	
	
}
