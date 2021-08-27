package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;


@Data
@Getter
@Setter
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(documentNumber, consumer.documentNumber)
                && Objects.equals(mobilePhoneNumber, consumer.mobilePhoneNumber)
                && Objects.equals(residencePhoneNumber, consumer.residencePhoneNumber)
                && Objects.equals(phoneNumber, consumer.phoneNumber)
                && Objects.equals(number, consumer.number)
                && Objects.equals(portalCode, consumer.portalCode)
                && Objects.equals(foodCardNumber, consumer.foodCardNumber)
                && Double.compare(consumer.foodCardBalance, foodCardBalance) == 0
                && Objects.equals(fuelCardNumber, consumer.fuelCardNumber) && Double.compare(consumer.fuelCardBalance, fuelCardBalance) == 0
                && Objects.equals(drugstoreNumber, consumer.drugstoreNumber) && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country);
    }


}
