package br.com.alelo.consumer.consumerpat.model;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.response.CardResponse;

import java.math.BigDecimal;

public class CardMockEntity {
    public static Card cardBuilder() {
        Card card = new Card();
        card.setCardType(CardType.FOOD);
        card.setBalance(BigDecimal.valueOf(200));
        card.setId(1);
        card.setNumber("21349182048129104");
        card.setConsumer(ConsumerMockEntity.consumerBuilder());

        return card;
    }

    public static CardResponse cardResponseBuilder() {
        CardResponse cardResponse = new CardResponse();
        cardResponse.setCardType(CardType.FOOD);
        cardResponse.setBalance(BigDecimal.valueOf(200));
        cardResponse.setId(1);
        cardResponse.setNumber("21349182048129104");
        cardResponse.setConsumer(ConsumerMockEntity.consumerBuilder());

        return cardResponse;
    }
}
