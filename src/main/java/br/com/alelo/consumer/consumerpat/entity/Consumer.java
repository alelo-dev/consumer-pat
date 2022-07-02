package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;
import java.util.Objects;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    int documentNumber;
    Date birthDate;

    //contacts
    int mobilePhoneNumber;
    int residencePhoneNumber;
    int phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    int portalCode;

    //cards
    int foodCardNumber;
    double foodCardBalance;

    int fuelCardNumber;
    double fuelCardBalance;

    int drugstoreNumber;
    double drugstoreCardBalance;

    public Consumer() {
    }

    public Consumer(String name, int documentNumber, Date birthDate, int mobilePhoneNumber, int residencePhoneNumber, int phoneNumber, String email, String street, int number, String city, String country, int portalCode, int foodCardNumber, double foodCardBalance, int fuelCardNumber, double fuelCardBalance, int drugstoreNumber, double drugstoreCardBalance) {
        this.name = name;
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
        this.drugstoreNumber = drugstoreNumber;
        this.drugstoreCardBalance = drugstoreCardBalance;
    }

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

    @Override
    public String toString() {
        return "{" +
            "name='" + getName() + "'" +
            ", documentNumber='" + getDocumentNumber() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", mobilePhoneNumber='" + getMobilePhoneNumber() + "'" +
            ", residencePhoneNumber='" + getResidencePhoneNumber() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", street='" + getStreet() + "'" +
            ", number='" + getNumber() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", portalCode='" + getPortalCode() + "'" +
            "}";
    }
}
