package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;


@Data
@Entity
@NoArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    int documentNumber;
    LocalDate birthDate;

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

    //fuel
    int fuelCardNumber;
    double fuelCardBalance;

    //drugStore
    int drugstoreNumber;
    double drugstoreCardBalance;

    public Consumer(ConsumerPostDTO consumerPostDTO) {
        this.name = consumerPostDTO.name;
        this.documentNumber = consumerPostDTO.documentNumber;
        this.birthDate = consumerPostDTO.birthDate;
        this.mobilePhoneNumber = consumerPostDTO.mobilePhoneNumber;
        this.residencePhoneNumber = consumerPostDTO.residencePhoneNumber;
        this.phoneNumber = consumerPostDTO.phoneNumber;
        this.email = consumerPostDTO.email;
        this.street = consumerPostDTO.street;
        this.number = consumerPostDTO.number;
        this.city = consumerPostDTO.city;
        this.country = consumerPostDTO.country;
        this.portalCode = consumerPostDTO.portalCode;
        this.foodCardNumber = consumerPostDTO.foodCardNumber;
        this.foodCardBalance = consumerPostDTO.foodCardBalance;
        this.fuelCardNumber = consumerPostDTO.fuelCardNumber;
        this.fuelCardBalance = consumerPostDTO.fuelCardBalance;
        this.drugstoreNumber = consumerPostDTO.drugstoreNumber;
        this.drugstoreCardBalance = consumerPostDTO.drugstoreCardBalance;
    }

    public Consumer(ConsumerPutDTO consumerPutDTO) {
        this.name = consumerPutDTO.name;
        this.documentNumber = consumerPutDTO.documentNumber;
        this.birthDate = consumerPutDTO.birthDate;
        this.mobilePhoneNumber = consumerPutDTO.mobilePhoneNumber;
        this.residencePhoneNumber = consumerPutDTO.residencePhoneNumber;
        this.phoneNumber = consumerPutDTO.phoneNumber;
        this.email = consumerPutDTO.email;
        this.street = consumerPutDTO.street;
        this.number = consumerPutDTO.number;
        this.city = consumerPutDTO.city;
        this.country = consumerPutDTO.country;
        this.portalCode = consumerPutDTO.portalCode;
        this.foodCardNumber = consumerPutDTO.foodCardNumber;
        this.fuelCardNumber = consumerPutDTO.fuelCardNumber;
        this.drugstoreNumber = consumerPutDTO.drugstoreNumber;
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


}
