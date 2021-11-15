package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.exception.ConsumerCardException;
import br.com.alelo.consumer.consumerpat.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @RequestMapping(value = "/setcardbalance", method = RequestMethod.PUT)
    public void setBalance(int cardNumber, double value) throws ConsumerCardException {
        cardService.setCardBalance(cardNumber, value);
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.PUT)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        cardService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
