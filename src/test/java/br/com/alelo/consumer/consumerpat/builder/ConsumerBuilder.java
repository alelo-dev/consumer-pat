package br.com.alelo.consumer.consumerpat.builder;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static br.com.alelo.consumer.consumerpat.builder.CardBuilder.*;

public final class ConsumerBuilder {
    private ConsumerBuilder() {
    }

    public static Consumer consumerWithValidCard() {
        final Card card = validCard();
        final Consumer consumer = new Consumer();
        consumer.setCards(Collections.singletonList(card));
        return consumer;
    }

    public static Consumer consumerWithNullCard() {
        final Consumer consumer = new Consumer();
        consumer.setCards(Collections.singletonList(null));
        return consumer;
    }

    public static Consumer consumerWithEmptyCard() {
        final Consumer consumer = new Consumer();
        consumer.setCards(Collections.singletonList(emptyCard()));
        return consumer;
    }

    public static Consumer consumerWith2Cards() {
        final Consumer consumer = new Consumer();
        consumer.setId(1L);
        consumer.setCards(new ArrayList<>(Arrays.asList(card1(), card2())));
        return consumer;
    }

    public static Consumer emptyConsumer() {
        return new Consumer();
    }
}
