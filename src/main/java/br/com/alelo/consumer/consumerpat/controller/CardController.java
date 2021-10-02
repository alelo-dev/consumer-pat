package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cards")
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping(value = "/setcardbalance")
    public ResponseEntity<Card> setBalance(@RequestParam String cardNumber,
                                           @RequestParam Double value) {

        return ResponseEntity.ok().body(service.setBalance(cardNumber, value));
    }

}
