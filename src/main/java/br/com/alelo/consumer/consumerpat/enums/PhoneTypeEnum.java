package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneTypeEnum {
    MOBILE(1),
    RESIDENCE(2);

    private Integer value;
}
