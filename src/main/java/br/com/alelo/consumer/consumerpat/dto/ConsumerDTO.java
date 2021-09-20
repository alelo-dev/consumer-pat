package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ConsumerDTO implements Serializable {
    static final long SerialVersionUID = 1L;

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

    public ConsumerDTO(Consumer c) {
        this.id = c.getId();
        this.name = c.getName();
        this.documentNumber = c.getDocumentNumber();
        this.birthDate = c.getBirthDate();
        this.mobilePhoneNumber = c.getMobilePhoneNumber();
        this.residencePhoneNumber = c.getResidencePhoneNumber();
        this.phoneNumber = c.getPhoneNumber();
        this.email = c.getEmail();
        this.street = c.getStreet();
        this.number = c.getNumber();
        this.city = c.getCity();
        this.country = c.getCountry();
        this.portalCode = c.getPortalCode();
        this.foodCardNumber = c.getFoodCardNumber();
        this.foodCardBalance = c.getFoodCardBalance();
        this.fuelCardNumber = c.getFuelCardNumber();
        this.fuelCardBalance = c.getFuelCardBalance();
        this.drugstoreNumber = c.getDrugstoreCardNumber();
        this.drugstoreCardBalance = c.getDrugstoreCardBalance();
    }
}
