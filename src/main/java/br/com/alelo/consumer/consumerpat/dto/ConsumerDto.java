package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class ConsumerDto {
	Integer id;
    String name;
    Integer documentNumber;
    Date birthDate;

    //contacts
    Integer mobilePhoneNumber;
    Integer residencePhoneNumber;
    Integer phoneNumber;
    String email;

    //Address
    String street;
    Integer number;
    String city;
    String country;
    Integer portalCode;

    //cards
    Integer foodCardNumber;
    BigDecimal foodCardBalance;

    Integer fuelCardNumber;
    BigDecimal fuelCardBalance;

    Integer drugstoreNumber;
    BigDecimal drugstoreCardBalance;
}
