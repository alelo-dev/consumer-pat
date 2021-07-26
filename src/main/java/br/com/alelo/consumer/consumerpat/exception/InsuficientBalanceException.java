package br.com.alelo.consumer.consumerpat.exception;

public class InsuficientBalanceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InsuficientBalanceException() {
        super("Insuficient balance.");
    }

}