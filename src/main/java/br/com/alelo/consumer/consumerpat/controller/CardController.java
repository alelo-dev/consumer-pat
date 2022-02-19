package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.card.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.vo.BuyVo;
import br.com.alelo.consumer.consumerpat.vo.CardVo;
import br.com.alelo.consumer.consumerpat.vo.ExtractVo;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping
    public CardVo createCard(@RequestBody CardVo cardVo) {
        return this.service.createCard(cardVo);
    }

    @PostMapping("/balance")
    public CardVo setBalance(@RequestBody CardVo cardVo) {
        return this.service.setBalance(cardVo);
    }

    @PostMapping("/buy")
    public ExtractVo buy(@RequestBody BuyVo buyVo) {
        return this.service.buy(buyVo);
    }
}
