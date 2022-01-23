package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateConsumerDTO {

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
    
}
