package br.com.alelo.consumer.consumerpat.entity;


import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
