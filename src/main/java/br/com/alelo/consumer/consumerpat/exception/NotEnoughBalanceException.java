package br.com.alelo.consumer.consumerpat.exception;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException() {
        super("Not enough balance");
    }
}
