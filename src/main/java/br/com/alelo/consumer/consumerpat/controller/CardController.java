package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.services.CardService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cards")
public class CardController {

    private static final String RETORNA_SUCESSO = "Retorna sucesso";
    private static final String OBJETO_NAO_ENCONTRADO = "Foi uma exceção de objeto não encontrado";
    private static final String FOI_UM_ERRO_INTERNO = "Foi gerado um erro interno";

    @Autowired
    private CardService service;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 404, message = OBJETO_NAO_ENCONTRADO),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @PostMapping(value = "/setCardbalance")
    public ResponseEntity<Card> setBalance(@RequestParam String cardNumber,
                                           @RequestParam Double value) {

        return ResponseEntity.ok().body(service.setBalance(cardNumber, value));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 404, message = OBJETO_NAO_ENCONTRADO),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @PostMapping(value = "/buy")
    public ResponseEntity<Card> buy(@RequestBody Purchase obj) {
        return ResponseEntity.ok().body(service.buy(obj));
    }

}
