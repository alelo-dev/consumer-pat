package br.com.alelo.consumer.consumerpat.domain.exception;

public class BusinessException extends Exception {
    BusinessException(String message) {
        super(message);
    }
}
