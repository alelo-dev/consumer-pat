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
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/card-transactions")
@Api(value = "Transações")
public class CardTransactionController {

	@Autowired
	ICardTransactionService service;
	
	@Operation(summary = "Listagem de Transações", description = "Utilize a listagem paginada para exibição das transações de cada cartão.")
	@GetMapping("/{cardNumber}")
	public Page<CardTransactionDTO> pageTransactions(
			@Parameter(description = "Número da página", required = false) @RequestParam(value="page", defaultValue="0") Integer page, 
			@Parameter(description = "Quantidade de itens por página", required = false) @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, 
			@Parameter(description = "Número do Cartão", required = false) @PathVariable String cardNumber) {
		return service.pageTransactions(page, linesPerPage, cardNumber);
	}
}
