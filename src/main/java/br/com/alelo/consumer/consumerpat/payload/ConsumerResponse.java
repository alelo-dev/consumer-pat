package br.com.alelo.consumer.consumerpat.payload;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Collection;

@Builder
@Getter
public class ConsumerResponse{

    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private ConsumerAddressResponse consumerAddress;

    //cards
    private Collection<ConsumerCardResponse> consumerCards;

}