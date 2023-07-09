package br.com.alelo.consumer.consumerpat.enuns;

import lombok.Getter;

@Getter
public enum CardTypeEnum {

    FOOD(1, "FOOD"),
    FUEL(2, "FUEL"),
    DRUGSTORE(3, "DRUGSTORE");

    private final Integer id;
    private final String name;

    CardTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
