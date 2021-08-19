package br.com.alelo.consumer.consumerpat.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.model.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.model.dto.SetBalanceDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
	
	private final CardService service;
	

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/cardList")
    public ResponseEntity<List<Card>> listAllCardrs() {
    	return ResponseEntity.ok(service.listAllCards());
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createCard")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
    	return ResponseEntity.ok(service.createCard(card));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/updateCard")
    public ResponseEntity<Card> updateConsumer(@RequestBody Card card) {
    	return ResponseEntity.ok(service.updateCard(card));
    }
	
    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping(value = "/setCardBalance")
    public ResponseEntity<Card> setBalance(@RequestBody SetBalanceDTO setBalanceDTO) {
    	Card card = service.setBalance(setBalanceDTO);
    	if(card == null) {
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(card);
    }
    
    @ResponseBody
    @GetMapping(value = "/buy")
    public void buy(@RequestBody BuyDTO buyDTO) {
    	/* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
    	 *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
    	 *
    	 * Tipos de estabelcimentos
    	 * 1 - Alimentação (food)
    	 * 2 - Farmácia (DrugStore)
    	 * 3 - Posto de combustivel (Fuel)
    	 */    	
    	try {
    		service.buy(buyDTO);
    		ResponseEntity.ok();
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
    }

}
