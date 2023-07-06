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

    int drugstoreCardNumber;
    double drugstoreCardBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return this.documentNumber == consumer.documentNumber
            && this.mobilePhoneNumber == consumer.mobilePhoneNumber
            && this.residencePhoneNumber == consumer.residencePhoneNumber
            && this.phoneNumber == consumer.phoneNumber
            && this.number == consumer.number
            && this.portalCode == consumer.portalCode
            && this.foodCardNumber == consumer.foodCardNumber
            && Double.compare(consumer.foodCardBalance, this.foodCardBalance) == 0
            && this.fuelCardNumber == consumer.fuelCardNumber && Double.compare(consumer.fuelCardBalance, this.fuelCardBalance) == 0
            && this.drugstoreCardNumber == consumer.drugstoreCardNumber && Double.compare(consumer.drugstoreCardBalance, this.drugstoreCardBalance) == 0
            && Objects.equals(this.id, consumer.id) && Objects.equals(this.name, consumer.name) && Objects.equals(this.birthDate, consumer.birthDate)
            && Objects.equals(this.email, consumer.email) && Objects.equals(this.street, consumer.street) && Objects.equals(this.city, consumer.city)
            && Objects.equals(this.country, consumer.country);
    }

}
