package br.com.alelo.consumer.consumerpat.domain.card.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum CardType {
    FOOD(1), DRUGSTORE(2), FUEL(3);

    private final int id;
}
