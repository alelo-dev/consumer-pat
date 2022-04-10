package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alelo.consumer.consumerpat.dto.AddBalanceDto;
import br.com.alelo.consumer.consumerpat.dto.BalanceCardDto;
import br.com.alelo.consumer.consumerpat.dto.NewCardDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.service.impl.CardService;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/card")
@ApiOperation(tags = "Card", value = "Card", notes = "Card Operations")
public class CardController {
	
	@Autowired
	CardService cardService;

	@ApiOperation(value = "Create new Card for user.")
	@PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Card> newCard(@RequestBody NewCardDto newCardDto) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(cardService.createNewCard(newCardDto));
	}
	
	@ApiOperation(value = "Consult expecific balance of the Card from user.")
	@PostMapping(path = "/balance", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Card> balance(@RequestBody BalanceCardDto balanceCardDto) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(cardService.checkBalance(balanceCardDto));
	}
		
	@ApiOperation(value = "Delete a exists card.")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception {
		return cardService.deleteCard(id);
	}

	@ApiOperation(value = "recarge balance to especifc card.")
	@PostMapping(path = "/recharge", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Card> recharge(@RequestBody AddBalanceDto addBalanceDto) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(cardService.recharge(addBalanceDto));
	}
	
}
