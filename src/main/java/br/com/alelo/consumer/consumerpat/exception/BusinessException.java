package br.com.alelo.consumer.consumerpat.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    HttpStatus statusCode;

    String errorCode;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Exception e) {
        super(message, e);
    }

    public BusinessException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public BusinessException(String message, String errorCode, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public BusinessException(String message, String errorCode, HttpStatus statusCode, Exception e) {
        super(message, e);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}
