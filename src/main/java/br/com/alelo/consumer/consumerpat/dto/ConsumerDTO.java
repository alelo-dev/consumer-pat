package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConsumerDTO {
    Integer id;
    String name;
    String documentNumber;
    Date birthDate;

    //contacts
    String mobilePhoneNumber;
    String residencePhoneNumber;
    String phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    String portalCode;

    //cards
    String foodCardNumber;
    String fuelCardNumber;
    String drugstoreNumber;
}
