package br.com.alelo.consumer.consumerpat.entity;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    BigDecimal foodCardBalance;

    String fuelCardNumber;
    BigDecimal fuelCardBalance;

    String drugStoreNumber;
    BigDecimal drugStoreCardBalance;
}
