package br.com.alelo.consumer.consumerpat.integration.rest.controller.dto.request;

import br.com.alelo.consumer.consumerpat.domain.entity.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class ConsumerRequestPostV1 {

    private String name;
    private String documentNumber;
    private LocalDate birthDate;
    private AddressRequestV1 address;
    private ContactRequestV1 contact;
    private Set<CardRequestPostV1> cards;


    public static Consumer transformToConsumer(ConsumerRequestPostV1 consumerRequestPostV1) {

        Address address = Address.builder()
                .city(consumerRequestPostV1.getAddress().getCity())
                .country(consumerRequestPostV1.getAddress().getCountry())
                .number(consumerRequestPostV1.getAddress().getNumber())
                .portalCode(consumerRequestPostV1.getAddress().getPortalCode())
                .street(consumerRequestPostV1.getAddress().getStreet())
                .build();

        Contact contact = Contact.builder()
                .email(consumerRequestPostV1.getContact().getEmail())
                .mobilePhoneNumber(consumerRequestPostV1.getContact().getMobilePhoneNumber())
                .phoneNumber(consumerRequestPostV1.getContact().getPhoneNumber())
                .residencePhoneNumber(consumerRequestPostV1.getContact().getResidencePhoneNumber())
                .build();

        //Uso set para garantir que não haverá duplicidade.
        Set<Card> cards = consumerRequestPostV1
                .getCards()
                .stream()
                .map(card -> Card
                        .builder()
                        .balance(card.getBalance())
                        .number(card.getNumber())
                        .type(CardType.valueOf(card.getType()))
                        .build())
                .collect(Collectors.toSet());

        return Consumer.builder()
                .documentNumber(consumerRequestPostV1.getDocumentNumber())
                .birthDate(consumerRequestPostV1.getBirthDate())
                .name(consumerRequestPostV1.getName())
                .address(address)
                .contact(contact)
                .cards(cards)
                .build();
    }

}
