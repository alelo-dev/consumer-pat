package br.com.alelo.consumer.consumerpat.util;

import br.com.alelo.consumer.consumerpat.entity.Card;

public final class CardValidation {

    public static void validate(Card card) {
        shouldNotBeNull(card);
        shouldHaveNumber(card);
    }

    public static void shouldNotBeNull(Card card) {
        ObjectUtils.shouldNotBeNull(card, Constants.CARD);
    }

    public static void shouldHaveNumber(Card card) {
        shouldHaveNumber(card.getNumber());
    }

    public static void shouldHaveNumber(String number) {
        ObjectUtils.shouldNotBeNull(number, Constants.CARD_NUMBER);
    }

}
