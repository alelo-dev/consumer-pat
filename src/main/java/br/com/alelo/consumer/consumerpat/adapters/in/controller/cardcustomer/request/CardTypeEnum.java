package br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CardTypeEnum {
    FOOD, DRUGSTORE, FUEL;

    @JsonValue
    public String value() {
        return name();
    }

    @JsonCreator
    public static CardTypeEnum fromString(String value) {
        return CardTypeEnum.valueOf(value.toUpperCase());
    }
}
