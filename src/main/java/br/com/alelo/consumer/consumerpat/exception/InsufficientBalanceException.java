package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super();
    }
    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
    public InsufficientBalanceException(String message) {
        super(message);
    }
    public InsufficientBalanceException(Throwable cause) {
        super(cause);
    }
}
