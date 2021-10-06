package br.com.alelo.consumer.consumerpat.resource.impl;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.resource.CardResource;
import br.com.alelo.consumer.consumerpat.services.impl.CardServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/cards")
public class CardController implements CardResource {

    private static final String CARD_SERVICE_METODO   = "CARD_SERVICE ::: Entrou no método";

    @Autowired
    private CardServiceImpl service;

    public ResponseEntity<Card> setBalance(@RequestParam String cardNumber,
                                           @RequestParam Double value) {
        log.info(CARD_SERVICE_METODO + " setBalance");
        return ResponseEntity.ok().body(service.setBalance(cardNumber, value));
    }

    public ResponseEntity<Card> buy(@RequestBody Purchase obj) {
        log.info(CARD_SERVICE_METODO + " buy");
        return ResponseEntity.ok().body(service.buy(obj));
    }

}
