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
    private int documentNumber;
    private Date birthDate;

    // contacts
    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;

    // Address
    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;

    // cards
    private int foodCardNumber;
    private double foodCardBalance;

    private int fuelCardNumber;
    private double fuelCardBalance;

    private int drugstoreNumber;
    private double drugstoreCardBalance;

}
