package br.com.alelo.consumer.consumerpat.infra.handler;

import br.com.alelo.consumer.consumerpat.infra.handler.exception.badRequest.BadRequestException;
import br.com.alelo.consumer.consumerpat.infra.handler.exception.notFound.NotFoundException;
import br.com.alelo.consumer.consumerpat.infra.handler.exception.unprocessableEntity.UnprocessableEntityException;
import br.com.alelo.consumer.consumerpat.infra.handler.model.ErrorModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerAdviceHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorModel handler(final BadRequestException exception) {
        return exception.getErrorModel();
    }
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorModel handler(final NotFoundException exception) {
        return exception.getErrorModel();
    }
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableEntityException.class)
    public ErrorModel handler(final UnprocessableEntityException exception) {
        return exception.getErrorModel();
    }

}
