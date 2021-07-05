package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BussinessException;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CardController {

    private final @NonNull
    CardService cardService;

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @GetMapping(value = "/setcardbalance")
    public void setBalance(Long cardNumber, Double value) throws BussinessException {
        cardService.setBalance(cardNumber, value);
    }

    @ResponseBody
    @GetMapping(value = "/buy")
    public Extract buy(int establishmentType, String establishmentName,
                               Long cardNumber, String productDescription, Double value) throws BussinessException {
        return cardService.buy(establishmentType,establishmentName,cardNumber,productDescription,value);
    }
}
