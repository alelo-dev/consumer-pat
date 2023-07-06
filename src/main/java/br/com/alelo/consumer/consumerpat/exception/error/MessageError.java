package br.com.alelo.consumer.consumerpat.exception.error;

import lombok.Getter;

@Getter
public class MessageError {

    private String error;

    public MessageError(String error) {
        this.error = error;
    }
}
