package br.com.alelo.consumer.consumerpat.controller.v2.controller;

import br.com.alelo.consumer.consumerpat.controller.v2.resources.CardResource;
import br.com.alelo.consumer.consumerpat.domain.dto.v2.BuyDTO;
import br.com.alelo.consumer.consumerpat.domain.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.EstablishmentInvalidException;
import br.com.alelo.consumer.consumerpat.domain.exception.InsuficientBalanceException;
import br.com.alelo.consumer.consumerpat.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController implements CardResource {

    @Autowired
    private CardService cardService;

    @Override
    public void setBalance(int cardNumber, double value) throws CardNotFoundException, InsuficientBalanceException {
        cardService.setBalance(cardNumber, value);
    }

    @Override
    public ResponseEntity<BuyDTO> buy(BuyDTO buyDTO) throws InsuficientBalanceException, EstablishmentInvalidException, CardNotFoundException {
        var extract = cardService.buy(buyDTO);
        return ResponseEntity.ok(BuyDTO.convertEntityToDto(extract));
    }
}
