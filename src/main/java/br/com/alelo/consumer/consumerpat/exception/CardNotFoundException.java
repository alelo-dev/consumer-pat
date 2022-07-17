package br.com.alelo.consumer.consumerpat.exception;

public class CardNotFoundException extends RuntimeException {

    private int id;

    public CardNotFoundException(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
