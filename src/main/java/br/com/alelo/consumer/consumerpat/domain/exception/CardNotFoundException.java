package br.com.alelo.consumer.consumerpat.domain.exception;

public class CardNotFoundException extends BusinessException {

    public CardNotFoundException(String message) {
        super(message);
    }
}
