package br.com.alelo.consumer.consumerpat.dto.response;


import lombok.*;

import java.util.Date;


@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerResponse {

    Integer id;
    String name;
    String documentNumber;
    Date birthDate;

    String mobilePhoneNumber;
    String residencePhoneNumber;
    String phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    int postalCode;

    //cards
    int foodCardNumber;
    double foodCardBalance;

    int fuelCardNumber;
    double fuelCardBalance;

    int drugstoreCardNumber;
    double drugstoreCardBalance;
}
