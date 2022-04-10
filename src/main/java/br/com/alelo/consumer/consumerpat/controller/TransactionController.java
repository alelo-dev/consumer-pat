package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alelo.consumer.consumerpat.dto.AddBalanceDto;
import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.ExtractDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.service.impl.TransactionService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/transaction")
@ApiOperation(tags = "Transaction", value = "Transaction", notes = "Transaction Operations")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@ApiOperation(value = "Add balance to especifc card.")
	@PostMapping(path = "/card/addBalance", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Card> addBalance(@RequestBody AddBalanceDto addBalanceDto) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.addBalance(addBalanceDto));
	}

	@ApiOperation(value = "Realize buy with card.")
	@PostMapping(path = "/buy", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExtractDto> buy(@RequestBody BuyDto buyDto) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.buy(buyDto));
	}
	
	//TODO consult extract
//	@PostMapping(path = "/extract", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Extract> buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
//		return ResponseEntity.status(HttpStatus.OK).body(extractService.buy(establishmentType, establishmentName, cardNumber, productDescription, value));
//	}
	



}
