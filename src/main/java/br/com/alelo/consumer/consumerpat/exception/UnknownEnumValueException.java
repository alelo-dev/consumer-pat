package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnknownEnumValueException extends RuntimeException {

    public UnknownEnumValueException(String message) {
        super(message);
    }
}
