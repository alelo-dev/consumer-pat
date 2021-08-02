package br.com.alelo.consumer.consumerpat.exception;

public class InsufficientFundsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InsufficientFundsException() {
        super("Insufficient funds");
    }

}
