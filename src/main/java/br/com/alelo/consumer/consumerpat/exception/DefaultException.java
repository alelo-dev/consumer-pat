package br.com.alelo.consumer.consumerpat.exception;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DefaultException extends RuntimeException implements Supplier<DefaultException>{

    private static final long serialVersionUID = 1L;
    public HttpStatus httpStatus;
    public String message;

    public DefaultException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
    
    public DefaultException() {}

    @Override
    public DefaultException get() {
        return this;
    }
    
    public ErrorResponse getErrorResponse() {
    
    	ErrorResponse errorResponse = new ErrorResponse(this.httpStatus, this.message);
        
        return errorResponse;
    }   
    
}
