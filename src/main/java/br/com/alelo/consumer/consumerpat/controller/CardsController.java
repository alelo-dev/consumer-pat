package br.com.alelo.consumer.consumerpat.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.dto.BalanceDto;
import br.com.alelo.consumer.consumerpat.dto.SaleDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.service.CardService;


@Controller
@RequestMapping("/cards")
public class CardsController {

    private final CardService service;

    @Autowired
    public CardsController(CardService service) {
        this.service = service;
    }


    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Card> listAllCardsByConsumer(String consumerName) {
        return this.service.findAllCards(consumerName);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createCard(@RequestBody Card card) {
        final var newCard = this.service.createCard(card);

        final var location = URI.create("/cards/".concat(newCard.getId().toString()));
        return ResponseEntity.created(location).build();
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/{cardId}", method = RequestMethod.PUT)
    public void updateCard(@RequestBody Card card, @PathVariable("cardId") Integer cardId) {
        this.service.updateCard(card, cardId);
    }

    @ResponseBody
    @RequestMapping(value = "/balance", method = RequestMethod.POST)
    public ResponseEntity<String> updateBalance(@RequestBody BalanceDto balanceDto) {
        final var card = this.service.updateBalance(balanceDto);

        if (card != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<Card> buy(@RequestBody SaleDto saleDto) {
        final var card = ResponseEntity.ok(this.service.processBuy(saleDto));
        
        if (card != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
