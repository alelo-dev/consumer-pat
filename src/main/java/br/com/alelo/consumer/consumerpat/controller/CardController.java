package br.com.alelo.consumer.consumerpat.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.domain.payload.BuyPayload;
import br.com.alelo.consumer.consumerpat.domain.response.ApiErrorResponse;
import br.com.alelo.consumer.consumerpat.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

	 private final CardService service;
	 
	 @PatchMapping(value = "/{cardNumber}/balance")
	 @ResponseStatus(code = HttpStatus.ACCEPTED)
	 @ApiOperation(value = "Add credit to given card number")
	 @ApiResponses(value = {
			 @ApiResponse(code = 202, message = "Success", response = Void.class),
			 @ApiResponse(code = 404, message = "Card with given number not found", response = ApiErrorResponse.class)
	 })	 
	 public void setBalance(@PathVariable("cardNumber") @ApiParam(example = "5513570566784056") String cardNumber, 
			 @ApiParam(example = "320.00") @RequestBody BigDecimal value) {
	     service.setBalance(cardNumber, value);
	 }
	 
	 @PatchMapping("/{cardNumber}/buy")
	 @ResponseStatus(code = HttpStatus.ACCEPTED)
	 @ApiOperation(value = "Generate a new transaction using given card number", consumes = MediaType.TEXT_PLAIN_VALUE)
	 @ApiResponses(value = {
			 @ApiResponse(code = 202, message = "Success", response = Void.class),
			 @ApiResponse(code = 404, message = "Card with given number not found", response = ApiErrorResponse.class),
			 @ApiResponse(code = 409, message = "Card and Establishment types mismatch", response = ApiErrorResponse.class)
	 })
	 public void buy(@PathVariable @ApiParam(example = "5513570566784056") String cardNumber, @RequestBody BuyPayload payload) {
	 	service.buy(cardNumber, payload);
	 }
	
}
