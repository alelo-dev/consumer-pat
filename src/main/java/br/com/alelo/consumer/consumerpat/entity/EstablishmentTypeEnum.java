package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstablishmentTypeEnum {

    FOOD("Alimentação"),
    FUEL("Posto de Combustível"),
    DRUGSTORE("Farmácia");

    private final String description;
}
