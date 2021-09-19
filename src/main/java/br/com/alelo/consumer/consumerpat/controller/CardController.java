package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.business.CardBusiness;
import br.com.alelo.consumer.consumerpat.model.dto.DtoBuy;
import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardBusiness cardBusiness;

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PatchMapping(value = "/balance/{cardNumber}")
    public void setBalance(
        @PathVariable("cardNumber")long cardNumber,
        @RequestParam(value = "value") double value) {
        cardBusiness.setBalance(cardNumber, value);
    }

    @PostMapping(value = "/buy")
    public void buy(@RequestBody @Validated DtoBuy dtoBuy) throws ProcessException {
        cardBusiness.buy(dtoBuy);
    }

}
