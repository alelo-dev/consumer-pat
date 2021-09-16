package br.com.alelo.consumer.consumerpat.domain.exception;

public class IndustryMismatchException extends BusinessException {
    public IndustryMismatchException() {
        super("industry mismatch");
    }
}
