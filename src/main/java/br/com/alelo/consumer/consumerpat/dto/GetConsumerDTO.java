package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;

import lombok.Data;

@Data
public class GetConsumerDTO {
    
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
    private double foodCardBalance;

    private int fuelCardNumber;
    private double fuelCardBalance;

    private int drugstoreNumber;
    private double drugstoreCardBalance;
    
}
