package br.com.alelo.consumer.consumerpat.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessError {
    RESOURCE_NOT_FOUND(1_001, HttpStatus.NOT_FOUND, "Resource not found");

    private int code;
    private HttpStatus httpStatus;
    private String description;

}
