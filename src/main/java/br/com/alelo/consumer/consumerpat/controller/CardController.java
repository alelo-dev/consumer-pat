package br.com.alelo.consumer.consumerpat.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alelo.consumer.consumerpat.dto.BuyInfoDto;
import br.com.alelo.consumer.consumerpat.dto.CardBalanceDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.CardService;

@Controller
@RequestMapping("/card")
public class CardController {
	@Autowired
	private CardService cardService;
	
	@RequestMapping(value = "/balance/update", method = RequestMethod.POST)
    public ResponseEntity setBalance(@RequestBody CardBalanceDto cardBalance) {
		 this.cardService.setBalance(cardBalance);
		 return new ResponseEntity(HttpStatus.NO_CONTENT);
	 }
	
	@ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity buy(@RequestBody BuyInfoDto buyInfo) {
        this.cardService.buy(buyInfo);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
