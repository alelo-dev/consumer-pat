package br.com.alelo.consumer.consumerpat.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice 
public class ConsumerExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     *
     */
    private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
    private static final String CONSTANT_VALIDATION_LENGTH = "Length";
    private static final String CONSTANT_VALIDATION_PATTERN = "Pattern";
    private static final String CONSTANT_VALIDATION_MIN = "Min";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Error> erros = gerarListaDeErros(ex.getBindingResult());

        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
            WebRequest request) {
        String userMessage = "Recurso não encontrado.";
        String devMessage = ex.toString();
        List<Error> erros = Arrays.asList(new Error(userMessage, devMessage));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            WebRequest request) {
        String userMessage = "Recurso não encontrado.";
        String devMessage = ex.toString();
        List<Error> erros = Arrays.asList(new Error(userMessage, devMessage));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(BusinessRulesException.class)
    public ResponseEntity<Object> handleRegraDeNegocioException(BusinessRulesException ex,
            WebRequest request) {
        String userMessage = ex.getMessage();
        String devMessage = ex.getMessage();
        List<Error> erros = Arrays.asList(new Error(userMessage, devMessage));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> gerarListaDeErros(BindingResult bindingResult) {
        List<Error> erros = new ArrayList<Error>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String userMessage = handleErrorMessageForUser(fieldError);
            String devMessage = fieldError.toString();
            erros.add(new Error(userMessage, devMessage));
        });
        return erros;
    }

    private String handleErrorMessageForUser(FieldError fieldError) {
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)) {
            return fieldError.getDefaultMessage().concat(" é obrigatório.");
        }
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)) {
            return fieldError.getDefaultMessage().concat(" é obrigatório.");
        }
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)) {
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres.",
                    fieldError.getArguments()[2], fieldError.getArguments()[1]));
        }
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_PATTERN)) {
            return fieldError.getDefaultMessage().concat(" formato inválido.");
        }
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_MIN)) {
            return fieldError.getDefaultMessage().concat(String.format(" deve ser maior ou igual a %s.",
                    fieldError.getArguments()[1]));
        }
        return fieldError.toString();
    }
}
