package br.com.alelo.consumer.consumerpat.helper;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.CardType;

import java.util.Random;

public class CardHelper {

    public static Card buildCard(CardType cardType){
        Random random = new Random();
        return Card.builder()
                .type(cardType)
                .number(random.nextLong() % (9999999999L - 1000000000L) + 9999999999L)
                .balance(10.0)
                .build();
    }

}
