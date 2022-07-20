package br.com.alelo.consumer.consumerpat.controller.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsumerRequest {

    String name;
    int documentNumber;
    Date birthDate;

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
    int fuelCardNumber;
    int drugstoreNumber;
}
