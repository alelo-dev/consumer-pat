package br.com.alelo.consumer.pat.controller;

import br.com.alelo.consumer.pat.domain.EstablishmentType;
import br.com.alelo.consumer.pat.payload.CardPayload;
import br.com.alelo.consumer.pat.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping(value = "/{consumerId}")
    public List<CardPayload> findByConsumerId(@PathVariable("consumerId") Long consumerId) {
        return cardService.findByConsumerId(consumerId);
    }

    @PostMapping(value = "{consumerId}/addAll")
    public void createCards(@PathVariable("consumerId") Long consumerId) {
        cardService.createCards(consumerId);
    }

    @PostMapping(value = "{consumerId}/addByType")
    public void createCardByType(@PathVariable("consumerId") Long consumerId, @RequestParam("cardType") EstablishmentType cardType) {
        cardService.createCardByType(consumerId, cardType);
    }

}
