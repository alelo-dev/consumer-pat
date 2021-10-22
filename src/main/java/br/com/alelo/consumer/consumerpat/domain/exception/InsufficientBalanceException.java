package br.com.alelo.consumer.consumerpat.domain.exception;

public class InsufficientBalanceException extends BusinessException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
