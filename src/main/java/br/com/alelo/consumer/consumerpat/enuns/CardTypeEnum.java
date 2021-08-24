package br.com.alelo.consumer.consumerpat.enuns;

import lombok.Getter;

@Getter
public enum CardTypeEnum {

    INDEFINIDO(0) , FOOD(1), FUEL(2), DRUGSTORE(3);

    private Integer value;

    private CardTypeEnum(Integer value) {
        this.value = value;
    }

    public static CardTypeEnum getEnum(Integer value) {
        for(CardTypeEnum v : values())
            if(v.getValue().equals(value)) return v;
        throw new IllegalArgumentException();
    }
}
