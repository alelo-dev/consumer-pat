package br.com.alelo.consumer.pat.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends RuntimeException {
    private String message;
}
