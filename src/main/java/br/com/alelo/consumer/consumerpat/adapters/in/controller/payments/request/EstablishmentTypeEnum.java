package br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstablishmentTypeEnum {
    FOOD,
    DRUGSTORE,
    FUEL;

    @JsonValue
    public String value() {
        return name();
    }

    @JsonCreator
    public static EstablishmentTypeEnum fromString(String value) {
        return EstablishmentTypeEnum.valueOf(value.toUpperCase());
    }
}
