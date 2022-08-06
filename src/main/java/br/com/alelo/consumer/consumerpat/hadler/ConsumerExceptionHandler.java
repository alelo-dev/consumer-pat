package br.com.alelo.consumer.consumerpat.hadler;

import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ConsumerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ConsumerRequestValidationException.Fields> fields = new ArrayList<>();

        for (ObjectError error : ex.getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            fields.add(new ConsumerRequestValidationException.Fields(fieldName, message));
        }

        ConsumerRequestValidationException exception = ConsumerRequestValidationException.builder()
                .status(status.value())
                .dateTime(OffsetDateTime.now())
                .title("One or more fields are invalid")
                .fields(fields)
                .build();

        return this.handleExceptionInternal(ex, exception, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleChallengeRewardBusinessException(BusinessException e, WebRequest request) {

        String detailMessage = e.getCause() == null ? "" : e.getCause().getMessage();

        log.error(e.getMessage() + " " + detailMessage);

        HttpStatus errorHttpStatus = e.getStatusCode() == null ? HttpStatus.UNPROCESSABLE_ENTITY : e.getStatusCode();

        ConsumerApiResponseException response = new ConsumerApiResponseException(
                errorHttpStatus.value(),
                errorHttpStatus,
                e.getMessage(),
                e.getErrorCode(),
                detailMessage
        );

        return handleExceptionInternal(e, response, new HttpHeaders(), errorHttpStatus, request);
    }

}
