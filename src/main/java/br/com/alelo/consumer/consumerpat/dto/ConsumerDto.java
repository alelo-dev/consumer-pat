package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConsumerDto {

    Integer id;
    String name;
    Integer documentNumber;
    Date birthDate;
    Integer mobilePhoneNumber;
    Integer residencePhoneNumber;
    Integer phoneNumber;
    String email;
    String street;
    Integer number;
    String city;
    String country;
    Integer portalCode;
    Integer foodCardNumber;
    Double foodCardBalance;
    Integer fuelCardNumber;
    Integer drugstoreNumber;
    Double drugstoreCardBalance;


}
