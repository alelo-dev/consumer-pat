package br.com.alelo.consumer.consumerpat.integration.rest.controller;

import br.com.alelo.consumer.consumerpat.domain.service.CardService;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request.CardBalanceRequestPostV1;
import br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.response.CardBalanceResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping(value = "/balance", method = RequestMethod.POST)
    public ResponseEntity<CardBalanceResponseV1> creditBalance(@RequestBody CardBalanceRequestPostV1 cardBalanceRequestPostV1) throws ApiException {
            return ResponseEntity.status(HttpStatus.CREATED).body(CardBalanceResponseV1.transformToResponse(cardService.creditBalance(cardBalanceRequestPostV1.getNumber(), cardBalanceRequestPostV1.getValue())));
    }

}
