package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardHistory;
import br.com.alelo.consumer.consumerpat.respository.CardHistoryRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardHistoryRepository cardHistoryRepository;
    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardHistoryRepository cardHistoryRepository, CardRepository cardRepository) {
        this.cardHistoryRepository = cardHistoryRepository;
        this.cardRepository = cardRepository;
    }

    @GetMapping(value = "/{cardNumber}/extract")
    public Page<CardHistory> extract(@PathVariable("cardNumber") Integer cardNumber, Pageable pageable) throws Exception {
        Optional<Card> card = cardRepository.findById(cardNumber);
        if(card.isPresent())
            return cardHistoryRepository.findAllByCard(card.get(),pageable);
        else
            throw new Exception("O numero do cartao não existe");
    }

    @PatchMapping(value = "/{cardNumber}/deposit")
    public void deposit(@PathVariable("cardNumber") Integer cardNumber,
                           @RequestBody BigDecimal value) throws Exception {
        Optional<Card> card = cardRepository.findById(cardNumber);
        if(card.isPresent())
            card.get().deposit(value);
        else
            throw new Exception("O numero do cartao não existe");
        cardRepository.save(card.get());
    }

    @PatchMapping(value = "/{cardNumber}/buy")
    public void buy(@PathVariable("cardNumber") Integer cardNumber,
                   @RequestBody BuyDTO value) throws Exception {
        Optional<Card> card = cardRepository.findById(cardNumber);
        if(card.isPresent())
            card.get().buy(value);
        else
            throw new Exception("O numero do cartao não existe");
        cardRepository.save(card.get());
    }
}
