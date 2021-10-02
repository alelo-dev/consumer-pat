package br.com.alelo.consumer.consumerpat.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardType {

    FOOD(0, "FOOD"),
    FUEL(1, "FUEL"),
    DRUG_STORE(2, "DRUG_STORE");

    private Integer code;
    private String description;
}
