package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request;

import br.com.alelo.consumer.consumerpat.domain.entity.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class ConsumerRequestPutV1 {

    private String consumerCode;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;
    private AddressRequestV1 address;
    private ContactRequestV1 contact;
    private Set<CardRequestPutV1> cards;

    public static Consumer transformToConsumer(ConsumerRequestPutV1 consumerRequestPutV1) {

        Address address = Address.builder()
                .city(consumerRequestPutV1.getAddress().getCity())
                .country(consumerRequestPutV1.getAddress().getCountry())
                .number(consumerRequestPutV1.getAddress().getNumber())
                .portalCode(consumerRequestPutV1.getAddress().getPortalCode())
                .street(consumerRequestPutV1.getAddress().getStreet())
                .build();

        Contact contact = Contact.builder()
                .email(consumerRequestPutV1.getContact().getEmail())
                .mobilePhoneNumber(consumerRequestPutV1.getContact().getMobilePhoneNumber())
                .phoneNumber(consumerRequestPutV1.getContact().getPhoneNumber())
                .residencePhoneNumber(consumerRequestPutV1.getContact().getResidencePhoneNumber())
                .build();

        //Uso set para garantir que não haverá duplicidade.
        Set<Card> cards = consumerRequestPutV1
                .getCards()
                .stream()
                .map(card -> Card
                        .builder()
                        .number(card.getNumber())
                        .type(CardType.valueOf(card.getType()))
                        .build())
                .collect(Collectors.toSet());

        return Consumer.builder()
                .documentNumber(consumerRequestPutV1.getDocumentNumber())
                .birthDate(consumerRequestPutV1.getBirthDate())
                .name(consumerRequestPutV1.getName())
                .address(address)
                .contact(contact)
                .cards(cards)
                .consumerCode(consumerRequestPutV1.getConsumerCode())
                .build();
    }

}
