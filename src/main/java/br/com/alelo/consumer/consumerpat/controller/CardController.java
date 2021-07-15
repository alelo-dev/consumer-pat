package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.service.CardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/card")
public class CardController extends BaseController<Card, CardService> {

    public CardController(CardService cardService) {
        super(cardService);
    }

    @ApiOperation(value = "Update card balance")
    @PatchMapping(value = "/balance/{cardNumber}/{value}")
    public void updateBalance(@PathVariable long cardNumber, @PathVariable BigDecimal value) {
        getService().updateBalance(cardNumber, value);
    }

    @ApiOperation(value = "Make a purchase with the card")
    @PutMapping(value = "/buy")
    public void buy(@RequestBody BuyDTO buyDTO) {
        getService().buy(buyDTO);
    }
}
