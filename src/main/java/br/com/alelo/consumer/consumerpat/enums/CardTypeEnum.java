package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardTypeEnum {
    FOOD(1),
    FUEL(2),
    DRUGSTORE(3);

    private Integer value;

}
