package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ServiceWarningException extends RuntimeException {
    public ServiceWarningException() {
        super();
    }
    public ServiceWarningException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceWarningException(String message) {
        super(message);
    }
    public ServiceWarningException(Throwable cause) {
        super(cause);
    }
}