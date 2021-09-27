package br.com.alelo.consumer.consumerpat.entity.enums;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum EstablishmentType {

    FOOD(1, "Alimentação"),
    DRUGSTORE(2, "Farmácia"),
    FUEL(3,"Posto de combustivel");

    private Integer code;
    private String name;

    public static EstablishmentType getEstablishmentTypeById(final Integer code) {
        return Stream.of(EstablishmentType.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
    }
}
