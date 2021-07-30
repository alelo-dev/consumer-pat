package br.com.alelo.consumer.consumerpat.builder;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enumerated.CardType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

public final class CardBuilder {
    private CardBuilder() {
    }

    public static Card validCard() {
        final Card card = new Card();
        card.setNumber("number");
        return card;
    }

    public static Card emptyCard() {
        return new Card();
    }

    public static Card card1() {
        final Card card = new Card();
        card.setId(1L);
        card.setNumber("#1");
        card.setBalance(BigDecimal.ONE);
        card.setTypes(new HashSet<>(Collections.singletonList(CardType.FOOD_CARD)));
        return card;
    }

    public static Card card2() {
        final Card card = new Card();
        card.setId(3L);
        card.setNumber("#3");
        card.setBalance(BigDecimal.TEN);
        card.setTypes(new HashSet<>(Collections.singletonList(CardType.FUEL_CARD)));
        return card;
    }
}
