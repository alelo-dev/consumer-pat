package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.enumerator.CardType;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping(CardController.CARD_ENDPOINT)
public class CardController {
    public static final String CARD_ENDPOINT = "/card";

    @Autowired
    CardService service;

    @PostMapping("/add/{cardNumber}")
    public CardDTO addCard(@PathVariable("cardNumber") final Integer cardNumber,
                    @RequestParam final CardType type,
                    @RequestParam final String documentNumber) {
        return service.add(cardNumber,
                            type,
                            documentNumber);
    }

    @PutMapping("/balance/{cardNumber}")
    public ResponseEntity<CardDTO> setBalance(@PathVariable("cardNumber") final Integer cardNumber,
                                              @RequestParam final Double value) {
         return service.setBalance(cardNumber, value);
    }
}
