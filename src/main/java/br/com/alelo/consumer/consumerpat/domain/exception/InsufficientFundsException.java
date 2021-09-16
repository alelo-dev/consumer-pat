package br.com.alelo.consumer.consumerpat.domain.exception;

public class InsufficientFundsException extends BusinessException {
    public InsufficientFundsException() {
        super("insufficient funds");
    }
}
