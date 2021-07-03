package br.com.alelo.consumer.consumerpat.domain.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ApiException extends Exception {
    private final HttpStatus status;
    private final Code code;
}
