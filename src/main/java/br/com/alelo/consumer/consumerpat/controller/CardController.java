package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.exception.CardNotAcceptedException;
import br.com.alelo.consumer.consumerpat.exception.InsufficientFundsException;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private ExtractService extractService;

    private static final String METHOD_INVOKED = "METHOD HAS BEEN INVOKED FROM CARDCONTROLLER: ";

    @PostMapping("/setCardBalance")
    @ApiOperation("Sets the card balance.")
    public ResponseEntity<Card> setBalance(@RequestParam String cardNumber,
                                           @RequestParam Double value) {
        log.info(METHOD_INVOKED + "setCardBalance");
        return ResponseEntity.ok().body(cardService.setBalance(cardNumber, value));
    }

    @ResponseBody
    @PostMapping("/buy")
    @ApiOperation("Makes a purchase.")
    public ResponseEntity<Extract> buy(Purchase purchase) throws InsufficientFundsException, CardNotAcceptedException {
        log.info(METHOD_INVOKED + "buy");
        cardService.buy(purchase);
        Extract extract = new Extract(purchase.getEstablishmentName(), purchase.getProductDescription(), LocalDateTime.now(), purchase.getCardNumber(), purchase.getValue());
        extractService.save(extract);

        return ResponseEntity.ok(extract);
    }

}
