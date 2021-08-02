package br.com.alelo.consumer.consumerpat.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EstablishmentType {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private final Integer id;

    public static EstablishmentType fromId(Integer id) {
        return Arrays.stream(EstablishmentType.values())
                .filter(e -> e.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported id %s.", id)));
    }

}
