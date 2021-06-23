package br.com.alelo.consumer.consumerpat.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Collection;

@Builder
@Getter
public class ConsumerDTO{

    private String name;
    private Integer documentNumber;
    private LocalDate birthDate;

    //contacts
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

    //cards
    private Collection<ConsumerCardDTO> consumerCards;

}