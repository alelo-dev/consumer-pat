package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.business.CardBusiness;
import br.com.alelo.consumer.consumerpat.contants.CardType;
import br.com.alelo.consumer.consumerpat.dto.BalanceRequest;
import br.com.alelo.consumer.consumerpat.dto.BuyRequest;
import br.com.alelo.consumer.consumerpat.dto.CardResponse;
import br.com.alelo.consumer.consumerpat.dto.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardBusiness cardBusiness;

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity buy(@RequestBody BuyRequest buy) {
        try {
            cardBusiness.buy(buy.getEstablishmentType(), buy.getEstablishmentName(), buy.getCardNumber(), buy.getProductDescription(), buy.getValue());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/balance", method = RequestMethod.PUT)
    public ResponseEntity setBalance(@RequestBody BalanceRequest balance) {
        Double saldoNovo = null;

        try {
            saldoNovo = cardBusiness.setBalance(balance.getCardNumber(), balance.getCardType(), balance.getValue());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(String.format("Saldo novo: R$ %s", saldoNovo));
    }

    @RequestMapping(value = "/extract/{cardType}/{cardNumber}", method = RequestMethod.GET)
    public @ResponseBody CardResponse getCardWithExtract(@PathVariable("cardType") CardType cardType, @PathVariable("cardNumber") String cardNumber) {
        return ResponseMapper.toCard(cardBusiness.getCardByCardNumberAndCardType(cardNumber, cardType, Boolean.TRUE));
    }
}
