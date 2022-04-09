package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.UpdateActionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("{cardNumber}/recharge")
    public <T> ResponseEntity<T> recharge(@PathVariable("cardNumber") String cardNumber,
                                            @RequestBody double value) {
        var actionResponse = cardService.recharge(cardNumber, value);
        if (UpdateActionResponse.NOT_FOUND.equals(actionResponse)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }


}
