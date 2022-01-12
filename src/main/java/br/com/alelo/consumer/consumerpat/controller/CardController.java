package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PatchMapping("/{cardNumber}/balance/{value}")
    public Card increaseBalance(@PathVariable BigInteger cardNumber, @PathVariable BigDecimal value) {
        return cardService.increaseBalance(cardNumber, value);
    }

    @PostMapping(value = "/{cardNumber}/charge/{value}")
    public Card charge(@PathVariable BigInteger cardNumber, @PathVariable BigDecimal value,
                       @RequestParam(required = true) String establishmentName,
                       @RequestParam(required = true) String productDescription) {

        return cardService.charge(establishmentName, cardNumber, productDescription, value);
    }
}
