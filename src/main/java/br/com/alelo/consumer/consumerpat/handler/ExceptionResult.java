package br.com.alelo.consumer.consumerpat.handler;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionResult {

    private LocalDateTime timestamp;
    private String message;

    public ExceptionResult() {
        timestamp = LocalDateTime.now();
    }

    public ExceptionResult(Throwable exception) {
        this();
        this.message = exception.getMessage();
    }

    public ExceptionResult(String message) {
        this();
        this.message = message;
    }
}
