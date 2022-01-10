package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class ConsumerCreateReq {

    @NotNull @NotEmpty(message = "Name is not empty")
    private String name;
    @NotNull
    private String documentNumber;
    private Date birthDate;
    private Set<ContactRequest> contacts;
    private Set<AddressRequest> address;
    private Set<CardRequest> cards;

    public Consumer toConsumer(){
       return Consumer.builder()
                .idConsumer(UUID.randomUUID().toString())
                .name(this.name)
                .documentNumber(this.documentNumber)
                .birthDate(this.birthDate)
                .contacts(toContact())
                .addresses(toAddress())
                .cards(toCard())
                .build();
    }

    private Set<Contact> toContact() {
        return this.contacts.stream()
                .map( c -> {
                            return Contact.builder()
                                    .idContact(UUID.randomUUID().toString())
                                    .residencePhoneNumber(c.getResidencePhoneNumber())
                                    .phoneNumber(c.getPhoneNumber())
                                    .email(c.getEmail())
                                    .mobilePhoneNumber(c.getMobilePhoneNumber())
                                    .build();
                }).collect(Collectors.toSet());
    }

    private Set<Address> toAddress() {
        return this.address.stream()
                .map(a -> {
                    return Address.builder()
                            .idAdress(UUID.randomUUID().toString())
                            .city(a.getCity())
                            .country(a.getCountry())
                            .number(a.getNumber())
                            .postalCode(a.getPostalCode())
                            .street(a.getStreet())
                            .build();
                }).collect(Collectors.toSet());
    }

    private Set<Card> toCard() {
        return this.cards.stream()
                .map(c -> {
                    return Card.builder()
                            .idCard(UUID.randomUUID().toString())
                            .cardBalance(c.getCardBalance())
                            .cardNumber(c.getCardNumber())
                            .typeCard(c.getTypeCard())
                            .build();
                }).collect(Collectors.toSet());
    }
}
