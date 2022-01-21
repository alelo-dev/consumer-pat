package br.com.alelo.consumer.consumerpat.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer documentNumber;
    private Date birthDate;

    //contacts
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private Integer portalCode;

    //cards
    private Integer foodCardNumber;
    private Double foodCardBalance;

    private Integer fuelCardNumber;
    private Double fuelCardBalance;

    private Integer drugstoreNumber;
    private Double drugstoreCardBalance;

}
