package br.com.alelo.consumer.consumerpat.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Extract;
import br.com.alelo.consumer.consumerpat.service.OperationService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(OperationController.BASE_PATH)
public class OperationController {
	
	/* URL base para Extract endpoints */
	public static final String BASE_PATH = "/api/v1";
	
	/* URL relativa para endpoint de operação de crédito */
	public static final String CREDIT = "/credits";

	/* URL relativa para endpoint de operação de débito */
	public static final String DEBIT = "/debits";
	
	private final OperationService operationService;
	
	@Autowired
	public OperationController(OperationService operationService) {
		this.operationService = operationService;
	}
	
	@PostMapping(value = CREDIT)
	@ApiOperation(value = "Credit operation")
    public ResponseEntity<Card> credit(String cardNumber, BigDecimal value) {
		final Card card;
		
		try {
			card = operationService.carryOutCredit(cardNumber, value);			
		} catch(Exception e) {		
			return ResponseEntity.badRequest().build();
		}
		
		return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping(value = DEBIT)
	@ApiOperation(value = "Debit operation")
    public ResponseEntity<Extract> debit(int establishmentType, String establishmentName, String cardNumber, String productDescription, BigDecimal value) {
		final Extract extract;
		
		try {
			extract = operationService.carryOutDebit(establishmentType, establishmentName, cardNumber, productDescription, value);			
		} catch(Exception e) {		
			return ResponseEntity.badRequest().build();
		}
		
		return new ResponseEntity<>(extract, HttpStatus.OK);
    }
}
