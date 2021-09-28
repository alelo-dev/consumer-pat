package br.com.alelo.consumer.consumerpat.controller.v2.resources;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.BuyDTO;
import br.com.alelo.consumer.consumerpat.domain.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.EstablishmentInvalidException;
import br.com.alelo.consumer.consumerpat.domain.exception.InsuficientBalanceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Card Operations")
@RequestMapping("/v2/cards")
public interface CardResource {

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Card value updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Add a value to the card",
                  notes = "Update the balance of a card, adding or removing the value from the actual balance")
    @PatchMapping("/{card-number}/{value}")
    void setBalance(@PathVariable("card-number") int cardNumber,
                    @PathVariable("value") double value) throws CardNotFoundException, InsuficientBalanceException;

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "OK", response = BuyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Buy a item with some card",
                  notes = "Buy something using the card")
    @PostMapping("/buy")
    ResponseEntity<BuyDTO> buy(@RequestBody BuyDTO buyDTO) throws InsuficientBalanceException, EstablishmentInvalidException, CardNotFoundException;
}

