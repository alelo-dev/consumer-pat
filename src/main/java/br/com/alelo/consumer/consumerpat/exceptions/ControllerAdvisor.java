package br.com.alelo.consumer.consumerpat.exceptions;

import br.com.alelo.consumer.consumerpat.exceptions.enums.CardTypeNotFoundException;
import br.com.alelo.consumer.consumerpat.exceptions.enums.EstablishmentTypeNotFoundException;
import br.com.alelo.consumer.consumerpat.exceptions.enums.PhoneTypeNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    private static final String TIMESTAMP = "timestamp";
    private static final String MESSAGE = "message";

    @ExceptionHandler(ConsumerNotFoundException.class)
    public ResponseEntity<Object> handleConsumerNotFoundException(ConsumerNotFoundException consumerNotFoundException, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, consumerNotFoundException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardNumberAlreadyExistsException.class)
    public ResponseEntity<Object> handleCardNumberAlreadyExistsException(CardNumberAlreadyExistsException cardNumberException, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, cardNumberException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardAndEstablishmentTypeInvalidException.class)
    public ResponseEntity<Object> handleCardAndEstablishmentTypeInvalidException(CardAndEstablishmentTypeInvalidException cardAndEstablishmentTypeInvalidException, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, cardAndEstablishmentTypeInvalidException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConsumerDocumentException.class)
    public ResponseEntity<Object> handleConsumerDocumentException(ConsumerDocumentException consumerDocumentException, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, consumerDocumentException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<Object> handleCardNotFoundException(CardNotFoundException cardNotFoundException, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, cardNotFoundException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardTypeNotFoundException.class)
    public ResponseEntity<Object> handleCardTypeNotFoundException(CardTypeNotFoundException cardTypeNotFoundException, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, cardTypeNotFoundException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EstablishmentTypeNotFoundException.class)
    public ResponseEntity<Object> handleEstablishmentTypeException(EstablishmentTypeNotFoundException establishmentTypeNotFoundException, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, establishmentTypeNotFoundException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhoneTypeNotFoundException.class)
    public ResponseEntity<Object> handlePhoneTypeNotFoundException(PhoneTypeNotFoundException phoneTypeNotFoundException, WebRequest webRequest) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, phoneTypeNotFoundException.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
