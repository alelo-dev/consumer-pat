package br.com.alelo.consumer.consumerpat.exception;


import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private String details;

    public NotFoundException(String message, String details) {
        super(message);
        this.details = details;
    }
}