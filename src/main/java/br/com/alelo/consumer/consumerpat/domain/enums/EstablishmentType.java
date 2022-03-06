package br.com.alelo.consumer.consumerpat.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstablishmentType {
    FOOD(1, "FoodCard"),
    DRUGSTORE(2, "DrugstoreCard"),
    FUEL(3, "FuelCard");

    private Integer id;
    private String descricao;

}
