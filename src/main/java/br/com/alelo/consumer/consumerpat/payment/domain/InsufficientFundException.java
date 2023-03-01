package br.com.alelo.consumer.consumerpat.payment.domain;

public class InsufficientFundException extends RuntimeException {

    public InsufficientFundException(String message) {
        super(message);
    }
}
