package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class CardBalanceChangeNotAllowedException extends RuntimeException {

    public CardBalanceChangeNotAllowedException(Long cardNumber) {
        super(String.format("Not is allowed change the balance of the card %s", cardNumber));
    }
}