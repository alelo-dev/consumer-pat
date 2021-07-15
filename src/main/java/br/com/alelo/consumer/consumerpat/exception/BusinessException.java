package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
