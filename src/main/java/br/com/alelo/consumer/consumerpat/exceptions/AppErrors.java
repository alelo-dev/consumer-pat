package br.com.alelo.consumer.consumerpat.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum AppErrors {
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "error.server"),
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, 400, "error.badRequest"),
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, 401, "error.unauthorized"),
    FORBIDDEN_ERROR(HttpStatus.FORBIDDEN, 403, "error.forbidden"),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, 404, "error.notFound"),
    METHOD_NOT_ALLOWED_ERROR(HttpStatus.METHOD_NOT_ALLOWED, 405, "error.methodNotAllowed"),
    BINDING_VALIDATION_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, 905, "error.bindValidation"),

    // Application Errors
    INVALID_BALANCE(HttpStatus.BAD_REQUEST, 400, "error.invalidBalance"),
    CARD_NOT_FOUND(HttpStatus.NOT_FOUND, 400, "error.cardNotFound"),
    CONSUMER_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "error.consumerNotFound")
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String messageRes;
    private final Map<String, Object> details = new HashMap<>();
}
