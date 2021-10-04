package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cards")
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping(value = "/setCardbalance")
    public ResponseEntity<Card> setBalance(@RequestParam String cardNumber,
                                           @RequestParam Double value) {

        return ResponseEntity.ok().body(service.setBalance(cardNumber, value));
    }

    @PostMapping(value = "/buy")
    public ResponseEntity<Card> buy(@RequestBody Purchase obj) {
        return ResponseEntity.ok().body(service.buy(obj));
    }

}
