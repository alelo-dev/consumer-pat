package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "consumer")
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONSUMER_ID")
    private Integer consumerId;

    @Column(name = "CONSUMER_NAME")
    private String consumerName;

    @Column(name = "DOCUMENT_NUMBER")
    private int documentNumber;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    //contacts
    @Column(name = "MOBILE_PHONE_NUMBER")
    private int mobilePhoneNumber;

    @Column(name = "RESIDENCE_PHONE_NUMBER")
    private int residencePhoneNumber;

    @Column(name = "PHONE_NUMBER")
    private int phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    //Address
    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PORTAL_CODE")
    private int portalCode;

    //cards
    @Column(name = "FOOD_CARD_NUMBER")
    private int foodCardNumber;

    @Column(name = "FOOD_CARD_BALANCE")
    private double foodCardBalance;

    @Column(name = "FUEL_CARD_NUMBER")
    private int fuelCardNumber;

    @Column(name = "FUEL_CARD_BALANCE")
    private double fuelCardBalance;
}