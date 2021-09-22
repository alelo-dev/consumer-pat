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
    String documentNumber;
    Date birthDate;

    //contacts
    String mobilePhoneNumber;
    int residencePhoneNumber;
    String phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    int portalCode;

    //cards
    String foodCardNumber;
    double foodCardBalance;

    String fuelCardNumber;
    double fuelCardBalance;

    String drugstoreCardNumber;
    double drugstoreCardBalance;

    ResultadoDTO resultadoDTO;

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
        this.drugstoreCardNumber = c.getDrugstoreCardNumber();
        this.drugstoreCardBalance = c.getDrugstoreCardBalance();
    }
}
