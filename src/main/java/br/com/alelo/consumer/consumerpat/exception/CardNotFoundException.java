package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(Long cardNumber) {
        super(String.format("Card %s Not found!", cardNumber));
    }
}