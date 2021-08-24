package br.com.alelo.consumer.consumerpat.enuns;

import lombok.Getter;

@Getter
public enum PhoneTypeEnum {

    INDEFINIDO(0) , RESIDENCIAL(1), COMERCIAL(2), MOBILE(3);

    private Integer value;

    private PhoneTypeEnum(Integer value) {
        this.value = value;
    }

    public static PhoneTypeEnum getEnum(Integer value) {
        for(PhoneTypeEnum v : values())
            if(v.getValue().equals(value)) return v;
        throw new IllegalArgumentException();
    }

}
