package br.com.alelo.consumer.consumerpat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alelo.consumer.consumerpat.controller.validator.CardValidator;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	CardService cardService;

	@Autowired
	ExtractRepository extractRepository;

	@PatchMapping(value = "/setcardbalance")
	public ResponseEntity<String> setBalance(String cardNumber, Double value) {

		final String validated = CardValidator.validateSetBalance(cardNumber, value);
		if (Objects.isNull(validated)) {
			cardService.setBalance(cardNumber, value);
			return ResponseEntity.ok().build();
		} else {
			log.error("Falha validação");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validated);
		}
	}

	@PostMapping(value = "/buy")
	public void buy(Integer establishmentType, String establishmentName, String cardNumber, String productDescription,
			Double value) {
		cardService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
	}

}
