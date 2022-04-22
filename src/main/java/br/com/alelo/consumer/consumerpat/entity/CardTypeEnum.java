package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardTypeEnum {

    FOOD("Alimentação"),
    FUEL("Posto de Combustível"),
    DRUGSTORE("Farmácia");

    private final String description;
}
