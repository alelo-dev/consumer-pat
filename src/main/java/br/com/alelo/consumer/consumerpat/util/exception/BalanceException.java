package br.com.alelo.consumer.consumerpat.util.exception;

public class BalanceException extends Exception{
    public BalanceException(String message) {
        super(message);
    }

    public BalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
