package br.com.alelo.consumer.consumerpat.types;

import java.util.Arrays;

public enum EstablishmentTypeEnum {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3),
    NOT_FOUND(0);

    private final int value;

    EstablishmentTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static final EstablishmentTypeEnum getByValue(int value){
        return Arrays.stream(EstablishmentTypeEnum.values()).filter(establishmentTypeEnum ->
                establishmentTypeEnum.value == value).findFirst().orElse(NOT_FOUND);
    }
}
