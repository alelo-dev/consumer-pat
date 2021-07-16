package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/card")
public class CardController extends BaseController<Card, CardService> {

    public CardController(CardService cardService) {
        super(cardService);
    }

    @ApiOperation(value = "Update card balance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update balance successful"),
            @ApiResponse(code = 400, message = "Balance less than zero"),
            @ApiResponse(code = 404, message = "Card not found")}
    )
    @PatchMapping(value = "/balance/{cardNumber}/{value}")
    public void updateBalance(@PathVariable long cardNumber, @PathVariable BigDecimal value) {
        getService().updateBalance(cardNumber, value);
    }

    @ApiOperation(value = "Make a purchase with the card")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful purchase"),
            @ApiResponse(code = 400, message = "Card different from the type of establishment"),
            @ApiResponse(code = 400, message = "Insufficient balance for operation"),
            @ApiResponse(code = 404, message = "Card not found")}
    )
    @PutMapping(value = "/buy")
    public void buy(@RequestBody BuyDTO buyDTO) {
        getService().buy(buyDTO);
    }
}
