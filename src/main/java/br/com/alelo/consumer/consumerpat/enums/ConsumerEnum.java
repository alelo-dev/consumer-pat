package br.com.alelo.consumer.consumerpat.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;



@Getter
public enum ConsumerEnum {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    Integer typeNumber;

    ConsumerEnum(Integer typeNumber) {
        this.typeNumber = typeNumber;
    }


    public static Optional<ConsumerEnum> fromValue(int codigo) {
        return Arrays.stream(ConsumerEnum.values()).filter(type -> type.typeNumber.equals(codigo)).findFirst();
    }


}