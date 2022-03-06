package br.com.alelo.consumer.consumerpat.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstablishmentType {
    FOOD(1, "Food Establishment"),
    DRUGSTORE(2, "Drugstore Establishment"),
    FUEL(3, "Fuel Establishment");

    private Integer id;
    private String descricao;

}
