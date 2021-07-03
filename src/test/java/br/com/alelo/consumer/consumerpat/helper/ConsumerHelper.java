package br.com.alelo.consumer.consumerpat.helper;

import br.com.alelo.consumer.consumerpat.domain.entity.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ConsumerHelper {
    public static Address buildAddress() {
        return Address.builder()
                .street("Rua 1")
                .country("Brasil")
                .portalCode(14620000)
                .city("Orlandia")
                .number(50)
                .build();
    }

    public static Contact buildContact() {
        return Contact.builder()
                .phoneNumber(1637269042L)
                .residencePhoneNumber(1639204030L)
                .email("joao@email.com")
                .mobilePhoneNumber(16991930430L)
                .build();
    }


    public static Consumer buildConsumer() {
        Set<Card> cards = new HashSet<>();
        cards.add(CardHelper.buildCard(CardType.FOOD));
        cards.add(CardHelper.buildCard(CardType.DRUGSTORE));
        cards.add(CardHelper.buildCard(CardType.FUEL));
        return Consumer.builder()
                .name("Joao Silva")
                .documentNumber("1234567890")
                .birthDate(LocalDate.now())
                .contact(buildContact())
                .address(buildAddress())
                .cards(cards)
                .build();
    }
}
