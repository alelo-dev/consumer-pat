package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.model.Balance;
import br.com.alelo.consumer.consumerpat.model.purchase.Purchase;
import br.com.alelo.consumer.consumerpat.service.card.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cards")
@Api(tags = "Cards", value = "Card", description = "Gerenciamento de compras e saldo do cartão" )
public class CardController {

	 
	 @Autowired	
	 CardService cardService;	
	    
    /**
     * Credita saldo no cartão de arcordo com o número do cartão e sua categoria.
     * 
     * @param balance
     * @return ResponseEntity<String>
     */
	@ApiOperation(value = "Atualiza saldo.")    
    @PostMapping("/balances")
    public ResponseEntity<String> setBalance(@RequestBody Balance balance) { 
    	cardService.setBalance(balance);
    	return ResponseEntity.ok("Trasação efetuada com sucesso!");
    }   
    
    /**
     * Calcula compras realizadas de acordo com o tipo de estabelecimento.
     * 
     * @param purchase
     * @return ResponseEntity<String>
     */
	@ApiOperation(value = "Calcula compras e atualiza saldo.")    
    @PostMapping("/purchases")
    public ResponseEntity<String> purchase(@RequestBody Purchase purchase) {    	
    	cardService.purchase(purchase);
    	return ResponseEntity.ok("Trasação efetuada com sucesso!");
    }

}
