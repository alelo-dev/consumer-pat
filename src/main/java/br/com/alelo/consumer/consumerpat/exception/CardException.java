package br.com.alelo.consumer.consumerpat.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CardException extends Exception {

    private static final String CARD_NOT_FOUND = "card not found";

    public CardException(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static CardException notFound() {
        return new CardException(CARD_NOT_FOUND);
    }

}