package br.com.alelo.consumer.consumerpat.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOperationException extends RuntimeException {

    private static final long serialVersionUID = -2023713351285264189L;

    public InvalidOperationException(String exception){
        super(exception);
    }
}
