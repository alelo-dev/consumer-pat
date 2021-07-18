package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsumerPutDTO {


    Integer id;
    String name;
    int documentNumber;
    LocalDate birthDate;

    //contacts
    int mobilePhoneNumber;
    int residencePhoneNumber;
    int phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    int portalCode;
    int foodCardNumber;
    int fuelCardNumber;
    int drugstoreNumber;

}
