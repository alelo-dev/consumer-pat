package br.com.alelo.consumer.pat.exception;

public class CardNotFoundException extends BusinessException {

    public CardNotFoundException() {
        this("Cartão não encontrado!");
    }

    private CardNotFoundException(final String message) {
        super(message);
    }

}
