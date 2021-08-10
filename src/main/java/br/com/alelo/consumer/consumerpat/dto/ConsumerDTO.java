package br.com.alelo.consumer.consumerpat.dto;


import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;


@Data
@Component
public class ConsumerDTO {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumerDTO consumer = (ConsumerDTO) o;
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

    public Consumer mapToEntity(){
        Consumer entity = new Consumer();

        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setDocumentNumber(this.getDocumentNumber());
        entity.setBirthDate(this.getBirthDate());

        entity.setMobilePhoneNumber(this.getMobilePhoneNumber());
        entity.setResidencePhoneNumber(this.getResidencePhoneNumber());
        entity.setPhoneNumber(this.getPhoneNumber());
        entity.setEmail(this.getEmail());

        entity.setStreet(this.getStreet());
        entity.setNumber(this.getNumber());
        entity.setCity(this.getCity());
        entity.setCountry(this.getCountry());
        entity.setPortalCode(this.getPortalCode());

        entity.setFoodCardNumber(this.getFoodCardNumber());
        entity.setFoodCardBalance(this.getFoodCardBalance());

        entity.setFuelCardNumber(this.getFuelCardNumber());
        entity.setFuelCardBalance(this.getFuelCardBalance());

        entity.setDrugstoreNumber(this.getDrugstoreNumber());
        entity.setDrugstoreCardBalance(this.getDrugstoreCardBalance());

        return entity;
    }

}
