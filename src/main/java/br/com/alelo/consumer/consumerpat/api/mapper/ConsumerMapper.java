package br.com.alelo.consumer.consumerpat.api.mapper;

import static java.util.Optional.ofNullable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.api.dto.input.AddressInput;
import br.com.alelo.consumer.consumerpat.api.dto.input.CardInput;
import br.com.alelo.consumer.consumerpat.api.dto.input.ConsumerInput;
import br.com.alelo.consumer.consumerpat.api.dto.input.ContactInput;
import br.com.alelo.consumer.consumerpat.domain.model.Address;
import br.com.alelo.consumer.consumerpat.domain.model.Card;
import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.domain.model.Contact;

@Component
public class ConsumerMapper {

    public Consumer toDomainObject(ConsumerInput input) {
        Consumer consumer = Consumer.builder()
            .name(input.getName())
            .document(input.getDocument())
            .birthDate(input.getBirthDate())
            .contact(buildContact(input.getContact()))
            .address(buildAddress(input.getAddress()))
            .build();
        consumer.setCards(buildCards(input.getCards(), consumer));

        return consumer;
    }

    public Consumer copyToDomainObject(ConsumerInput input, Consumer consumer) {
        if (isNotBlank(input.getName()))
            consumer.setName(input.getName());

        if (isNotBlank(input.getDocument()))
            consumer.setDocument(input.getDocument());

        if (Objects.nonNull(input.getBirthDate()))
            consumer.setBirthDate(input.getBirthDate());

        if (Objects.nonNull(input.getAddress()))
            consumer.setAddress(buildAddress(input.getAddress()));

        if (Objects.nonNull(input.getContact()))
            consumer.setContact(buildContact(input.getContact()));

        return consumer;
    }

    private List<Card> buildCards(List<CardInput> cards, Consumer consumer) {
        return Stream.ofNullable(cards)
            .flatMap(Collection::stream)
            .map(inputCard -> {
                return Card.builder()
                        .number(inputCard.getNumber())
                        .balance(inputCard.getBalance())
                        .type(inputCard.getType())
                        .consumer(consumer)
                        .build();
        }).collect(Collectors.toList());
    }

    private Contact buildContact(ContactInput contact) {
        return ofNullable(contact)
            .map(contactInput -> {
                return Contact.builder()
                        .email(contactInput.getEmail())
                        .cellPhone(contactInput.getCellPhone())
                        .phone(contactInput.getPhone())
                        .build();
            }).orElse(null);
    }

    private Address buildAddress(AddressInput address) {
        return ofNullable(address)
            .map(addressInput -> {
                return Address.builder()
                        .street(addressInput.getStreet())
                        .number(addressInput.getNumber())
                        .city(addressInput.getCity())
                        .country(addressInput.getCountry())
                        .postalCode(addressInput.getPostalCode())
                        .build();
            }).orElse(null);
    }

    private boolean isNotBlank(String string) {
        return string != null && !string.isBlank();
    }
}
