package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.command.CreateConsumerCommand;
import br.com.alelo.consumer.consumerpat.command.UpdateConsumerCommand;

import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Consumer {

    @Id
    String id;
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

    public Consumer() {}

    public Consumer(CreateConsumerCommand command) {
        this.id = command.getId();
        this.name = command.getName();
        this.documentNumber = command.getDocumentNumber();
        this.birthDate = command.getBirthDate();
        this.mobilePhoneNumber = command.getMobilePhoneNumber();
        this.residencePhoneNumber = command.getResidencePhoneNumber();
        this.phoneNumber = command.getPhoneNumber();
        this.email = command.getEmail();
        this.street = command.getStreet();
        this.number = command.getNumber();
        this.city = command.getCity();
        this.country = command.getCountry();
        this.portalCode = command.getPortalCode();
        this.foodCardNumber = command.getFoodCardNumber();
        this.foodCardBalance = command.getFoodCardBalance();
        this.fuelCardNumber = command.getFuelCardNumber();
        this.fuelCardBalance = command.getFuelCardBalance();
        this.drugstoreNumber = command.getDrugstoreNumber();
        this.drugstoreCardBalance = command.getDrugstoreCardBalance();
    }

    public Consumer(UpdateConsumerCommand command) {
        this.id = command.getId();
        this.name = command.getName();
        this.documentNumber = command.getDocumentNumber();
        this.birthDate = command.getBirthDate();
        this.mobilePhoneNumber = command.getMobilePhoneNumber();
        this.residencePhoneNumber = command.getResidencePhoneNumber();
        this.phoneNumber = command.getPhoneNumber();
        this.email = command.getEmail();
        this.street = command.getStreet();
        this.number = command.getNumber();
        this.city = command.getCity();
        this.country = command.getCountry();
        this.portalCode = command.getPortalCode();
        this.foodCardNumber = command.getFoodCardNumber();
        this.fuelCardNumber = command.getFuelCardNumber();
        this.drugstoreNumber = command.getDrugstoreNumber();
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
