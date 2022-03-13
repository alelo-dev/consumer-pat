package br.com.alelo.consumer.consumerpat.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessError {
    RESOURCE_NOT_FOUND(1_001, HttpStatus.NOT_FOUND, "Resource not found"),
    UNAUTHORIZED_CARD_TRANSACTION(1_002, HttpStatus.INTERNAL_SERVER_ERROR, "Unauthorized card transaction");


    private int code;
    private HttpStatus httpStatus;
    private String description;

}
