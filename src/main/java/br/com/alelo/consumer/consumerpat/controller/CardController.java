package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/card", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@SuppressWarnings("squid:S4684")
public class CardController {
    private CardService cardService;

    private ExtractService extractService;

    @ResponseStatus(code = OK)
    @PatchMapping(value = "/update-balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Executado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Cartão não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao fazer o update do novo saldo")
    })
    public ResponseEntity<Card> setBalance(@RequestBody final Card card) {
        return ResponseEntity.ok(cardService.updateBalance(card.getNumber(), card.getBalance()));
    }

    @ResponseStatus(code = OK)
    @PatchMapping(value = "/buy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Executado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Cartão não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro na operação de compra")
    })
    public ResponseEntity<Extract> buy(@RequestBody final BuyDTO buyDTO) {
        final Card cardUpdated = cardService.buy(buyDTO);
        return ResponseEntity.ok(
                extractService.save(cardUpdated, buyDTO, LocalDate.now()));
    }
}
