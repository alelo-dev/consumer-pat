package br.com.alelo.consumer.consumerpat.model;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;

import java.time.LocalDate;
import java.util.Set;

public class ConsumerMockEntity {


    public static Consumer consumerBuilder() {

        Consumer consumer = new Consumer();
        consumer.setId(1);
        consumer.setName("GUILHERME PEREIRA");
        consumer.setBirthDate(LocalDate.of(1998,12,14));
        consumer.setDocumentNumber("000.000.000-00");
        consumer.setCards(Set.of());

        return consumer;
    }

    public static ConsumerRequest consumerRequestBuilder() {
        ConsumerRequest consumer = new ConsumerRequest();
        consumer.setId(1);
        consumer.setName("GUILHERME PEREIRA");
        consumer.setBirthDate(LocalDate.of(1998,12,14));
        consumer.setDocumentNumber("000.000.000-00");
        consumer.setCards(Set.of());
        consumer.setAddress(new Address());
        consumer.setContact(new Contact());

        return consumer;
    }

    public static ConsumerResponse consumerResponseBuilder() {
        ConsumerResponse consumer = new ConsumerResponse();
        consumer.setId(1);
        consumer.setName("GUILHERME PEREIRA");
        consumer.setBirthDate(LocalDate.of(1998,12,14));
        consumer.setDocumentNumber("000.000.000-00");
        consumer.setCards(Set.of());
        consumer.setAddress(new Address());
        consumer.setContact(new Contact());

        return consumer;
    }
}

