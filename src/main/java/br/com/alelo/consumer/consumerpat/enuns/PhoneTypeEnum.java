package br.com.alelo.consumer.consumerpat.enuns;

import lombok.Getter;

@Getter
public enum PhoneTypeEnum {

    RESIDENCIAL(1), COMERCIAL(2), MOBILE(3);

    private int value;

    PhoneTypeEnum(int value) {
        this.value = value;
    }

}
