package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ICardService;
import br.com.alelo.consumer.consumerpat.service.IExtractService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller of card
 *
 * @author mcrj
 */
@RestController
@RequestMapping(value = "/card", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CardController {

    private final ICardService cardService;

    private final IExtractService extractService;

    @ResponseStatus(code = OK)
    @PatchMapping(value = "/balance/{cardNumber}/{value}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully in the process"),
            @ApiResponse(responseCode = "204", description = "No content in the process"),
            @ApiResponse(responseCode = "400", description = "Occurred a fail trying to reach the result")
    })
    public ResponseEntity<Card> setBalance(@PathVariable final Long cardNumber,
                           @PathVariable final BigDecimal value) {
        return ResponseEntity.ok(cardService.updateBalance(cardNumber, value));
    }

    @ResponseStatus(code = OK)
    @PatchMapping(value = "/buy")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully in the process"),
            @ApiResponse(responseCode = "204", description = "No content in the process"),
            @ApiResponse(responseCode = "400", description = "Occurred a fail trying to reach the result"),
            @ApiResponse(responseCode = "403", description = "Not possible to continue in the process")
    })
    public ResponseEntity<Extract> buy(@RequestBody final RequestBuyDTO buyDTO) {
        final Card cardUpdated = cardService.buy(buyDTO);
        return ResponseEntity.ok(
                extractService.save(cardUpdated, buyDTO, LocalDate.now()));
    }
}
