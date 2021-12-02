package br.com.alelo.consumer.consumerpat.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorMessages {

    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("Validation failed", HttpStatus.BAD_REQUEST),
    CONFLICT("Entity already exists", HttpStatus.CONFLICT),
    NOT_FOUND("Entity not found", HttpStatus.NOT_FOUND),
    NOT_ACCEPTABLE("Validation failed", HttpStatus.NOT_ACCEPTABLE),
    PRECONDITION_FAILED("Precondition failed", HttpStatus.PRECONDITION_FAILED),
    FORBIDDEN("Access Denied", HttpStatus.FORBIDDEN),
    UNAUTHORIZED("Unauthorized", HttpStatus.UNAUTHORIZED),
    PAYLOAD_TOO_LARGE("Payload Too Large", HttpStatus.PAYLOAD_TOO_LARGE),
    TOO_MANY_REQUESTS("Too Many Requests", HttpStatus.TOO_MANY_REQUESTS);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorMessages(final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
