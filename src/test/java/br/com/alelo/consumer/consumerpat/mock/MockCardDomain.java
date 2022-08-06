package br.com.alelo.consumer.consumerpat.mock;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;
import br.com.alelo.consumer.consumerpat.request.CardRequest;
import br.com.alelo.consumer.consumerpat.response.CardResponse;

import java.math.BigDecimal;

public class MockCardDomain {

    public static Card buildCard() {
        return Card.builder()
                .cardtype(CardType.FOOD)
                .active(true)
                .cardBalance(BigDecimal.valueOf(500))
                .id(1L)
                .cardNumber("9557596836147377")
                .consumer(MockConsumerDomain.buildConsumer())
                .build();
    }

    public static CardResponse buildCardResponse() {
        return CardResponse.builder()
                .cardtype(CardType.FOOD)
                .active(true)
                .cardBalance(BigDecimal.valueOf(100))
                .id(1L)
                .cardNumber("9557596836147377")
                .build();
    }

    public static CardRequest buildCardRequest() {
        return CardRequest.builder()
                .cardtype(CardType.FOOD)
                .active(true)
                .cardBalance(BigDecimal.valueOf(100))
                .consumerId(1L)
                .build();
    }

    public static CardRequest buildCardRequestExistCard() {
        return CardRequest.builder()
                .cardtype(CardType.FOOD)
                .active(true)
                .cardBalance(BigDecimal.valueOf(100))
                .consumerId(2L)
                .build();
    }

    public static Card buildCardInactive() {
        return Card.builder()
                .cardtype(CardType.FOOD)
                .active(false)
                .cardBalance(BigDecimal.valueOf(100))
                .id(1L)
                .cardNumber("9557596836147377")
                .consumer(MockConsumerDomain.buildConsumer())
                .build();
    }


    public static Card buildCardNoLimit() {
        return Card.builder()
                .cardtype(CardType.FOOD)
                .active(true)
                .cardBalance(BigDecimal.valueOf(10))
                .id(1L)
                .cardNumber("9557596836147377")
                .consumer(MockConsumerDomain.buildConsumer())
                .build();
    }

    public static Card buildCardFuel() {
        return Card.builder()
                .cardtype(CardType.FUEL)
                .active(true)
                .cardBalance(BigDecimal.valueOf(500))
                .id(1L)
                .cardNumber("9557596836147377")
                .consumer(MockConsumerDomain.buildConsumer())
                .build();
    }

    public static Card buildCardActiveFalse() {
        return Card.builder()
                .cardtype(CardType.FUEL)
                .active(false)
                .cardBalance(BigDecimal.valueOf(500))
                .id(1L)
                .cardNumber("9557596836147377")
                .consumer(MockConsumerDomain.buildConsumer())
                .build();
    }

    public static CardResponse buildCardResponseActiveFalse() {
        return CardResponse.builder()
                .cardtype(CardType.FOOD)
                .active(false)
                .cardBalance(BigDecimal.valueOf(100))
                .id(1L)
                .cardNumber("9557596836147377")
                .build();
    }
}
