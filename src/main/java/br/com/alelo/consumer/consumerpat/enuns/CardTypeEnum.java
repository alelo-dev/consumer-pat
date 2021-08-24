package br.com.alelo.consumer.consumerpat.enuns;

import lombok.Getter;

@Getter
public enum CardTypeEnum {

    FOOD(1), FUEL(2), DRUGSTORE(3);

    private int value;

    CardTypeEnum(int value) {
        this.value = value;
    }
}
