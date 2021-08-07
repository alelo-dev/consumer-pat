package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.CardTransactionDTO;
import br.com.alelo.consumer.consumerpat.service.ICardTransactionService;

@RestController
@RequestMapping("/card-transactions")
public class CardTransactionController {

	@Autowired
	ICardTransactionService service;
	
	@GetMapping("/{cardNumber}")
	public Page<CardTransactionDTO> pageTransactions(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, 
			@PathVariable String cardNumber) {
		return service.pageTransactions(page, linesPerPage, cardNumber);
	}
}
