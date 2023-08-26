package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.service.CardCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class CardsCreditController {

    @Autowired
    private CardCreditService cardCreditService;

    @PatchMapping("/setcardbalance/{cardNumber}/{value}")
    public ResponseEntity<Void> setBalance(@PathVariable(name = "cardNumber") Integer cardNumber, @PathVariable(name = "value") Double value) {
        try {
            cardCreditService.setBalance(cardNumber, value);
            return ResponseEntity.ok().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

}
}