package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
