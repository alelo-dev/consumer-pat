package br.com.alelo.consumer.consumerpat.domain.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DomainException extends RuntimeException {
    public DomainException(final String message) {
        super(message);
    }
}
