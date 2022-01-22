package br.com.alelo.consumer.consumerpat.enums;

import br.com.alelo.consumer.consumerpat.exceptions.enums.PhoneTypeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PhoneTypeEnum {
    MOBILE(1),
    RESIDENCE(2);

    private Integer value;

    public static PhoneTypeEnum getEnum(final Integer phoneType) {
        return Arrays.stream(values()).filter(enumItem -> enumItem.getValue().equals(phoneType)).findFirst().orElseThrow(PhoneTypeNotFoundException::new);
    }
}
