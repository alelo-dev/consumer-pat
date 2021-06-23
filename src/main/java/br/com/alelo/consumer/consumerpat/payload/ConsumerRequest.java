package br.com.alelo.consumer.consumerpat.payload;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Collection;

@Builder
@Getter
public class ConsumerRequest{

    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private ConsumerAddressRequest consumerAddress;

    //cards
    private Collection<ConsumerCardRequest> consumerCards;

}