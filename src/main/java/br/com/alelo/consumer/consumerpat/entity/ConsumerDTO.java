package br.com.alelo.consumer.consumerpat.entity;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumerDTO {

    private Integer id;
    private String name;
    private int documentNumber;
    private Date birthDate;

    //contacts
    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;

    //Address
    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;

    //cards
    private int foodCardNumber;
    private int fuelCardNumber;
    private int drugstoreNumber;
    
}