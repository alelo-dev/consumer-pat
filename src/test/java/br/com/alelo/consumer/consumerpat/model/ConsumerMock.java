package br.com.alelo.consumer.consumerpat.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsumerMock {


    public static List<Consumer> getListOfConsumers() {
        List<Consumer> consumers = new ArrayList<>();
        consumers.add(getConsumer());
        return consumers;
    }

    public static Consumer getConsumer() {
        Consumer consumer = new Consumer();
        consumer.setBirthDate(LocalDate.now());
        consumer.setName("tobi");
        consumer.setDocumentNumber("222555566-X");
        consumer.setContact(getContact());
        consumer.setCards(getCards());
        return consumer;
    }

    public static List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        Card card = new Card();

        card.setFunds(BigDecimal.valueOf(1000));
        card.setNumber(BigInteger.valueOf(56565656565656L));
        card.setType(CardType.Drugstore);
        cards.add(card);

        card = new Card();
        card.setFunds(BigDecimal.valueOf(1000));
        card.setNumber(BigInteger.valueOf(56565656565655L));
        card.setType(CardType.Food);
        cards.add(card);

        card = new Card();
        card.setFunds(BigDecimal.valueOf(1000));
        card.setNumber(BigInteger.valueOf(56565656565654L));
        card.setType(CardType.Fuel);
        cards.add(card);

        return cards;
    }

    public static Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail("tobi@ggg.com");
        contact.setCountry("Brazil");
        contact.setCity("Sao Paulo");
        contact.setPostalCode("02050-090");
        contact.setMobileNumber("+55 11 9994-0000");
        contact.setStreet("street 505");
        contact.setTelephoneNumber("11 2244-0000");
        return contact;
    }

}