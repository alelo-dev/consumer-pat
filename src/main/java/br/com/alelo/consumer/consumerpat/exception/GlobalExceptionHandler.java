package br.com.alelo.consumer.consumerpat.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DefaultException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(DefaultException exception) {
    
        return new ResponseEntity<ErrorResponse>(exception.getErrorResponse(), exception.httpStatus);
        
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgNotValidException(MethodArgumentNotValidException exception) {

        String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);

        return ResponseEntity.badRequest().body(errorResponse);

    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException exception) {
          
        String exMessage = exception.getPathReference();
                    
        String errorMessage = exMessage.substring(exMessage.lastIndexOf("[") + 2, exMessage.indexOf("]") - 1);
            
        errorMessage = errorMessage + " precisa ser um número válido";
    
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    
        return ResponseEntity.badRequest().body(errorResponse);
            
    }
    
}
