package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

@Data
public class ErrorMessage {

    private final int code;
    private final String message;

}
