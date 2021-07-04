package br.com.alelo.consumer.consumerpat.infra.handler.exception.notFound;

import br.com.alelo.consumer.consumerpat.infra.handler.exception.ApiException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends ApiException {

    public NotFoundException(String msg) {
        super(msg, NOT_FOUND.value());
    }
}
