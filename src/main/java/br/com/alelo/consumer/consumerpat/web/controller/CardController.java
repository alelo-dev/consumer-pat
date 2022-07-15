package br.com.alelo.consumer.consumerpat.web.controller;

import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.web.vo.card.CardVO;
import br.com.alelo.consumer.consumerpat.web.vo.card.UpdateCardFormVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/card")
public class CardController {

    private final CardService cardService;

    @GetMapping("{cardNumber}")
    public CardVO findByCardNumber(@PathVariable Long cardNumber) {
        return CardVO.from(cardService.findByCardNumber(cardNumber));
    }

    @PutMapping("{cardNumber}/balance")
    public CardVO updateCardBalance(@PathVariable Long cardNumber, @Valid @RequestBody UpdateCardFormVO form) {
        return CardVO.from(cardService.updateCardBalance(cardNumber, form));
    }
}
