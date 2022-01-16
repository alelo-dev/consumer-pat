package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private TransactionService transactionService;

    @PatchMapping("/{cardNumber}/funds/{value}")
    public Card increaseFunds(@PathVariable BigInteger cardNumber, @PathVariable BigDecimal value) {
        return cardService.increaseFunds(cardNumber, value);
    }

    @PostMapping(value = "/{cardNumber}/charge/{value}")
    public Card charge(@PathVariable BigInteger cardNumber, @PathVariable BigDecimal value,
                       @RequestParam(required = true) String establishmentName,
                       @RequestParam(required = true) String productDescription) {

        Card card = cardService.charge(cardNumber, value);
        transactionService.save(establishmentName, cardNumber, productDescription, value);
        return card;
    }
}
