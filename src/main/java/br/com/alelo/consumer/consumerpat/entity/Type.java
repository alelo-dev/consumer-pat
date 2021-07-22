package br.com.alelo.consumer.consumerpat.entity;

import lombok.Getter;

@Getter
public enum Type {

    FOOD_CARD_NUMBER("food", 1),
    FUEL_CARD_NUMBER("fuel", 3),
    DRUGSTORE_CARD_NUMBER("drugstore", 2);

    private final String name;
    private final Integer code;

    Type(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

}
