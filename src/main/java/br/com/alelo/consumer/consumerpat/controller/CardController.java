package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.PUT)
    public void setBalance(BalanceDTO balance) {
        cardService.setBalance(balance);
    }
}
