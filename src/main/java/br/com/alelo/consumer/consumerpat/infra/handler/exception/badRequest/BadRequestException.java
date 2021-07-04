package br.com.alelo.consumer.consumerpat.infra.handler.exception.badRequest;

import br.com.alelo.consumer.consumerpat.infra.handler.exception.ApiException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Class comments go here...
 *
 * @author André Franco
 * @version 1.0 05/12/2020
 */
public class BadRequestException extends ApiException {


    public BadRequestException(final String msg) {
        super(msg, BAD_REQUEST.value());
    }

}
