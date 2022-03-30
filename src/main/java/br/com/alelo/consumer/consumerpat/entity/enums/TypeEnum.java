package br.com.alelo.consumer.consumerpat.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeEnum {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private final Integer id;
}
