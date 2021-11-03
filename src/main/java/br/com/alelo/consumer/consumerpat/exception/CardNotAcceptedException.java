package br.com.alelo.consumer.consumerpat.exception;

public class CardNotAcceptedException extends Exception {

    private static final long serialVersionUID = -1L;

    public CardNotAcceptedException() {
    }

    public CardNotAcceptedException(String message) {
        super(message);
    }
}
