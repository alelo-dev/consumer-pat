package br.com.alelo.consumer.consumerpat.dto.response;


import lombok.*;

import javax.persistence.Entity;
import java.util.Date;


@Data
@Entity
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerResponse {

    Integer id;
    String name;
    int documentNumber;
    Date birthDate;

    int mobilePhoneNumber;
    int residencePhoneNumber;
    int phoneNumber;
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

    int drugstoreNumber;
    double drugstoreCardBalance;
}
