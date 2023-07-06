package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.*;
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

    @Column(unique = true)
    int foodCardNumber;
    double foodCardBalance;


    @Column(unique = true)
    int fuelCardNumber;
    double fuelCardBalance;

    @Column(unique = true)
    int drugstoreNumber;
    double drugstoreCardBalance;

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
