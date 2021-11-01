package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.Column;
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
    Integer id;
    String name;
    @Column(unique = true)
    int documentNumber;
    Date birthDate;

    // contacts
    int mobilePhoneNumber;
    int residencePhoneNumber;
    int phoneNumber;
    String email;

    // Address
    String street;
    int number;
    String city;
    String country;
    int postalCode;

    // cards
    @Column(unique = true)
    int foodCardNumber;
    double foodCardBalance;

    @Column(unique = true)
    int fuelCardNumber;
    double fuelCardBalance;

    @Column(unique = true)
    int drugstoreNumber;
    double drugstoreCardBalance;

}
