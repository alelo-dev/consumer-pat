package br.com.alelo.consumer.consumerpat.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TypeEnum {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private final Integer id;

    public static TypeEnum valueOf(Integer id) {
        return Arrays.stream(TypeEnum.values()).filter(t ->
                        t.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid type."));
    }
}
