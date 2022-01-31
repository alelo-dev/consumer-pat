package br.com.alelo.consumer.consumerpat.domain.enumeration;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum CardType {

    FOOD(1), DRUGSTORE(2), FUEL(3);

    private Integer cardTypeId;

    CardType(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public static CardType getById(Integer cardTypeId) {
        return Stream.of(CardType.values())
                .filter(c -> c.getCardTypeId().equals(cardTypeId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


}
