package br.com.alelo.consumer.consumerpat.enumerated;

import br.com.alelo.consumer.consumerpat.util.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
@ToString
public enum CardType {
    FOOD_CARD(1, Constants.FOOD_CARD),
    DRUGSTORE_CARD(2, Constants.DRUGSTORE_CARD),
    FUEL_CARD(3, Constants.FUEL_CARD);

    private final int code;
    private final String description;

    public static CardType fromCode(Integer value) {
        return Arrays.stream(values())
                .filter(it -> Objects.equals(it.code, value))
                .findFirst()
                .orElse(null);
    }
}
