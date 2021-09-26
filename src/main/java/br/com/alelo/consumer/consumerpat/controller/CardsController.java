package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.service.CardsService;
import br.com.alelo.consumer.consumerpat.service.dto.response.CardsResponse;

@RestController
@RequestMapping("/api")
public class CardsController {

	@Autowired
	private CardsService cardsService;
	
	/*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
	@PutMapping("/setcardbalance")
	public ResponseEntity<CardsResponse> setBalance(EstablishmentTypeEnum typeEnum,int cardNumber, double value){
		return ResponseEntity.ok(cardsService.setBalance(typeEnum, cardNumber, value));
	}
}
