package br.com.alelo.consumerpat.entrypoint.v1;

import br.com.alelo.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumerpat.core.exception.InvalidEstablishmentForCardException;
import br.com.alelo.consumerpat.core.exception.InvalidRechargeException;
import br.com.alelo.consumerpat.core.usecase.BuyUseCase;
import br.com.alelo.consumerpat.core.usecase.CardRechargeUseCase;
import br.com.alelo.consumerpat.core.dto.v1.request.CardBuyV1RequestDto;
import br.com.alelo.consumerpat.core.dto.v1.request.CardRechargeV1RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/v1/cards")
public class CardResource {

    @Autowired
    private CardRechargeUseCase rechargeUseCase;

    @Autowired
    private BuyUseCase buyUseCase;

    @PutMapping("/recharge/{cardNumber}")
    public ResponseEntity<Void> recharge(@PathVariable("cardNumber") String cardNumber, @RequestBody CardRechargeV1RequestDto request) {
        try {
            this.rechargeUseCase.recharge(cardNumber, request);

            return ResponseEntity.noContent().build();
        } catch (CardNotFoundException | InvalidBalanceException | InvalidRechargeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/buys/{cardNumber}")
    public ResponseEntity<Void> buys(@PathVariable("cardNumber") String cardNumber, @RequestBody CardBuyV1RequestDto request)
            throws InvalidBalanceException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidEstablishmentForCardException, CardNotFoundException {
        try {
            this.buyUseCase.calculateBalance(cardNumber, request);

            return ResponseEntity.noContent().build();
        } catch (CardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
