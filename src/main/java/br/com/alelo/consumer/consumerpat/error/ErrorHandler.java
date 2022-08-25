package br.com.alelo.consumer.consumerpat.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorResponse> handle(MethodArgumentNotValidException validException){
        List<ErrorResponse> errors = new ArrayList<>();
        List<FieldError> fieldErrors = validException.getBindingResult().getFieldErrors();
        fieldErrors.forEach( e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            errors.add(new ErrorResponse(e.getField(), message ));
        });

        List<ObjectError> globalErrors = validException.getBindingResult().getGlobalErrors();
        globalErrors.forEach( ge -> errors.add(new ErrorResponse("", ge.getDefaultMessage())));
        return errors;
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConsumerException.class)
    public List<ErrorResponse> handleProfileCannotEditedException(ConsumerException exception){
        List<ErrorResponse> errors = new ArrayList<>();        
        errors.add(new ErrorResponse("", exception.getMessage() ));        
        return errors;
    }
}
