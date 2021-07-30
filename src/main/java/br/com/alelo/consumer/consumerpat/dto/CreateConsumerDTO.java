package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateConsumerDTO {
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;

    //cards
    private List<CreateCardDTO> cards;
}
