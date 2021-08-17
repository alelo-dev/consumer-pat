package br.com.alelo.consumer.consumerpat.exceptions.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Messages {
    TEMP("message.temp");

    @Getter
    private final String value;
}