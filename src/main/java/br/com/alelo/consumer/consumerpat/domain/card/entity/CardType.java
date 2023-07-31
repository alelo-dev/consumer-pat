package br.com.alelo.consumer.consumerpat.domain.card.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CardType {
    FOOD, DRUGSTORE, FUEL;

    @JsonValue
    public String value() {
        return name();
    }

    @JsonCreator
    public static CardType fromString(String value) {
        return CardType.valueOf(value.toUpperCase());
    }
}
