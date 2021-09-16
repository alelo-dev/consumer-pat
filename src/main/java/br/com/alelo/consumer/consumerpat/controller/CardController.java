package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.data.entity.Card;
import br.com.alelo.consumer.consumerpat.data.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.Balance;
import br.com.alelo.consumer.consumerpat.domain.Purchase;
import br.com.alelo.consumer.consumerpat.domain.exception.IndustryMismatchException;
import br.com.alelo.consumer.consumerpat.domain.exception.InsufficientFundsException;
import br.com.alelo.consumer.consumerpat.domain.exception.UnknownCardException;
import br.com.alelo.consumer.consumerpat.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @Value("${pagination.page.size.extract}")
    private int defaultExtractPageSize;

    @GetMapping("/{cardNumber}")
    public Card findCard(@PathVariable String cardNumber) throws UnknownCardException {
        return cardService.findByCardNumber(cardNumber);
    }

    @PostMapping("/{cardNumber}/balance")
    public void addBalance(@PathVariable String cardNumber, @RequestBody Balance balance) throws UnknownCardException {
        cardService.addBalance(cardNumber, balance.getValue());
    }

    @GetMapping("/{cardNumber}/extract")
    public Page<Extract> findCardExtract(@PathVariable String cardNumber, Integer size, Integer page) {
        return cardService.findCardExtract(
                cardNumber,
                size == null ? defaultExtractPageSize : size,
                page == null ? 0 : page
        );
    }

    @PostMapping("/buy")
    public Card buy(@RequestBody Purchase purchase) throws UnknownCardException, IndustryMismatchException, InsufficientFundsException {
        return cardService.buy(purchase);
    }
}