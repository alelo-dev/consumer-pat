package br.com.alelo.consumer.consumerpat.payment.domain;

public class PaymentTypeNotPermittedException extends RuntimeException {

    public PaymentTypeNotPermittedException(String message) {
        super(message);
    }
}
