package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;


@Data
@Entity(name = "CONSUMER")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DOCUMENT_NUMBER")
    private int documentNumber;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    //contacts TODO: Change This to another model?
    @Column(name = "MOBILE_PHONE_NUMBER")
    private int mobilePhoneNumber;

    @Column(name = "RESIDENCE_PHONE_NUMBER")
    private int residencePhoneNumber;

    @Column(name = "PHONE_NUMBER")
    private int phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    //Address TODO: Change This to another model?
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

    //cards TODO: Change This to another model?
    @Column(name = "FOOD_CARD_NUMBER")
    private int foodCardNumber;

    @Column(name = "FOOD_CARD_BALANCE")
    private double foodCardBalance;

    @Column(name = "FUEL_CARD_NUMBER")
    private int fuelCardNumber;

    @Column(name = "FUEL_CARD_BALANCE")
    private double fuelCardBalance;

    @Column(name = "DRUGSTORE_NUMBER")
    private int drugstoreNumber;

    @Column(name = "DRUGSTORE_CARD_BALANCE")
    private double drugstoreCardBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return documentNumber == consumer.documentNumber
                && mobilePhoneNumber == consumer.mobilePhoneNumber
                && residencePhoneNumber == consumer.residencePhoneNumber
                && phoneNumber == consumer.phoneNumber
                && number == consumer.number
                && portalCode == consumer.portalCode
                && foodCardNumber == consumer.foodCardNumber
                && Double.compare(consumer.foodCardBalance, foodCardBalance) == 0
                && fuelCardNumber == consumer.fuelCardNumber && Double.compare(consumer.fuelCardBalance, fuelCardBalance) == 0
                && drugstoreNumber == consumer.drugstoreNumber && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country);
    }


}
