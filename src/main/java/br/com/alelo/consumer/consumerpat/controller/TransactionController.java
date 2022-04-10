package br.com.alelo.consumer.consumerpat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.ExtractDto;
import br.com.alelo.consumer.consumerpat.dto.RequestExtractDto;
import br.com.alelo.consumer.consumerpat.service.impl.TransactionService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/transaction")
@ApiOperation(tags = "Transaction", value = "Transaction", notes = "Transaction Operations")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@ApiOperation(value = "Realize buy with card.")
	@PostMapping(path = "/buy", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExtractDto> buy(@RequestBody BuyDto buyDto) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.buy(buyDto));
	}

	@ApiOperation(value = "Consult all extract by user")
	@PostMapping(path = "/extract", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExtractDto>> extract(@RequestBody RequestExtractDto requestExtractDto) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(transactionService.consultExtract(requestExtractDto));
	}
	
}
