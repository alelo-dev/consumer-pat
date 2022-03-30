package br.com.alelo.consumer.consumerpat.enums;

import br.com.alelo.consumer.consumerpat.exception.EstablishmentTypeException;

import java.util.Arrays;

public enum EstablishmentTypeEnum {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private final Integer value;

    EstablishmentTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static EstablishmentTypeEnum get(Integer value) throws EstablishmentTypeException {
        return Arrays.stream(EstablishmentTypeEnum.values())
                .filter(e -> e.getValue().equals(value))
                .findFirst()
                .orElseThrow(EstablishmentTypeException::notFound);
    }

}
