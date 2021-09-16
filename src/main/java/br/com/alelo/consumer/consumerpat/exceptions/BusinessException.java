package br.com.alelo.consumer.consumerpat.exceptions;

import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = NOT_FOUND)
public class BusinessException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BusinessException() {
        super();
    }
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(final Throwable cause) {
        super(cause);
    }
}