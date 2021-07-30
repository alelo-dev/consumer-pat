package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BaseHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T> ResponseEntity<BaseHttpResponse<T>> of(T result, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(BaseHttpResponse.of(result));
    }
}
