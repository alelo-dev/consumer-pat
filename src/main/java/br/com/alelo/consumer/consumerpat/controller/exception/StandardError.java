package br.com.alelo.consumer.consumerpat.controller.exception;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status_code;
    private String message;
}
