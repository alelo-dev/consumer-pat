package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardType {
    FOOD(1, "Food"), DRUGSTORE(2, "Drugstore"), FUEL(3, "Fuel");
    Integer code;
    String type;
}
