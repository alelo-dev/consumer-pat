package br.com.alelo.consumer.consumerpat.application.controller.card;

import br.com.alelo.consumer.consumerpat.application.controller.card.payload.CardRechargeRequest;
import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    private final CardService cardService;

    @PostMapping(value = "/recharge")
    public ResponseEntity<Void> recharge(@Valid @RequestBody CardRechargeRequest cardRechargeRequest) {
        cardService.chargeCard(cardRechargeRequest.getCardNumber(), cardRechargeRequest.getAmount());
        return ResponseEntity.noContent().build();
    }

}
