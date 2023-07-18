package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "consumer")
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONSUMER_ID")
    private Integer consumerId;

    @Column(name = "CONSUMER_NAME")
    private String consumerName;

    @Column(name = "DOCUMENT_NUMBER", unique = true)
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

    @Column(name = "EMAIL", unique = true)
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
    @Column(name = "FOOD_CARD_NUMBER", unique = true)
    private int foodCardNumber;

    @Column(name = "FOOD_CARD_BALANCE")
    private double foodCardBalance;

    @Column(name = "FUEL_CARD_NUMBER", unique = true)
    private int fuelCardNumber;

    @Column(name = "FUEL_CARD_BALANCE")
    private double fuelCardBalance;

    @Column(name = "DRUGSTORE_CARD_NUMBER", unique = true)
    private int drugstoreCardNumber;

    @Column(name = "DRUGSTORE_CARD_BALANCE")
    private double drugstoreCardBalance;

    public Consumer(String consumerName, int documentNumber, Date birthDate, int mobilePhoneNumber, int residencePhoneNumber, int phoneNumber, String email, String street, int number, String city, String country, int portalCode, int foodCardNumber, double foodCardBalance, int fuelCardNumber, double fuelCardBalance, int drugstoreCardNumber, double drugstoreCardBalance) {
        this.consumerName = consumerName;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.residencePhoneNumber = residencePhoneNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
        this.foodCardNumber = foodCardNumber;
        this.foodCardBalance = foodCardBalance;
        this.fuelCardNumber = fuelCardNumber;
        this.fuelCardBalance = fuelCardBalance;
        this.drugstoreCardNumber = drugstoreCardNumber;
        this.drugstoreCardBalance = drugstoreCardBalance;
    }

    public Consumer(String consumerName, int documentNumber, Date birthDate, int mobilePhoneNumber, int residencePhoneNumber, int phoneNumber, String email, String street, int number, String city, String country, int portalCode, int foodCardNumber, int fuelCardNumber, int drugstoreCardNumber) {
        this.consumerName = consumerName;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.residencePhoneNumber = residencePhoneNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
        this.foodCardNumber = foodCardNumber;
        this.fuelCardNumber = fuelCardNumber;
        this.drugstoreCardNumber = drugstoreCardNumber;
    }
}