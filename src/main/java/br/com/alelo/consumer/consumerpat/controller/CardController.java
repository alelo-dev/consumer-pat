package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.request.CardBalanceUpdateRequestDto;
import br.com.alelo.consumer.consumerpat.dto.request.CardPurchaseRequestDto;
import br.com.alelo.consumer.consumerpat.service.CardBalanceUpdateService;
import br.com.alelo.consumer.consumerpat.service.CardPurchaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardBalanceUpdateService cardBalanceUpdateService;

    @Autowired
    CardPurchaseService cardPurchaseService;


    @ApiOperation(value = "Credita valor no cartao")
    @PutMapping("/balance")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void setBalance(@RequestBody CardBalanceUpdateRequestDto cardBalanceUpdateRequestDto) {
        cardBalanceUpdateService.setBalance(cardBalanceUpdateRequestDto);
    }

    @ApiOperation(value = "Registra No Cartao, Dados da Compra Efetuada")
    @PostMapping("/buy")
    @ResponseStatus(code = HttpStatus.OK)
    public void buy(@RequestBody CardPurchaseRequestDto cardPurchaseRequestDto) {
        cardPurchaseService.buy(cardPurchaseRequestDto);
    }
}
