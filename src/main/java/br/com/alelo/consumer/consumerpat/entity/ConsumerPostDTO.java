package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ConsumerPostDTO {

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

    //cards
    int foodCardNumber;
    double foodCardBalance;

    //fuel
    int fuelCardNumber;
    double fuelCardBalance;

    //drugStore
    int drugstoreNumber;
    double drugstoreCardBalance;
}
