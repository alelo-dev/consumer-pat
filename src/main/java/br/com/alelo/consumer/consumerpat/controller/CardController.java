package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.response.CardResponse;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @PatchMapping(value = "{number}/setBalance")
    public ResponseEntity<CardResponse> setBalance(@PathVariable("number") String cardNumber, BigDecimal value) {
        return new ResponseEntity<>(cardService.updateBalance(cardNumber, value), HttpStatus.OK);
    }
}
