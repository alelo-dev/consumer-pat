package br.com.alelo.consumer.consumerpat.service.exception;

public class InvalidTransactionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidTransactionException(String msg) {
        super(msg);
    }
}
