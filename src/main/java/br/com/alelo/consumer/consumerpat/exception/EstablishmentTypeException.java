package br.com.alelo.consumer.consumerpat.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EstablishmentTypeException extends Exception {

    private static final String NOT_FOUND = "not found";

    public EstablishmentTypeException(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static EstablishmentTypeException notFound() {
        return new EstablishmentTypeException(NOT_FOUND);
    }

}