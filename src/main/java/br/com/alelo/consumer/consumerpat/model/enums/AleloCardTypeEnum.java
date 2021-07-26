package br.com.alelo.consumer.consumerpat.model.enums;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 12:09
 */

import lombok.Getter;

@Getter
public enum AleloCardTypeEnum {

    FOOD(1, "Alimentação", 10),
    DRUG(2, "Farmácia", 0),
    FUEL(3, "Combustivel", 0);

    private Integer code;
    private String description;
    private Integer discountPercent;

    AleloCardTypeEnum(Integer code, String description, Integer discountPercent) {
        this.code = code;
        this.description = description;
        this.discountPercent = discountPercent;
    }
}
