package br.com.alelo.consumer.pat;

import br.com.alelo.consumer.pat.domain.EstablishmentType;
import br.com.alelo.consumer.pat.entity.Card;
import br.com.alelo.consumer.pat.entity.Consumer;

import java.math.BigDecimal;
import java.util.List;

public class ConsumerFactory {

    public static Consumer createWithAllCardsWithBalance(BigDecimal balance) {
        return Consumer.builder()
            .name("Consumer")
            .cards(List.of(
                    Card.builder()
                        .cardNumber("1000")
                        .establishmentType(EstablishmentType.DRUGSTORE)
                        .balance(balance)
                        .build(),

                    Card.builder()
                        .cardNumber("2000")
                        .establishmentType(EstablishmentType.FOOD)
                        .balance(balance)
                        .build(),

                    Card.builder()
                        .cardNumber("3000")
                        .establishmentType(EstablishmentType.FUEL)
                        .balance(balance)
                        .build())
                  ).build();
    }

}
