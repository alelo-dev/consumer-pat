package br.com.alelo.consumer.consumerpat.entity;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Consumer implements Serializable {
    private static final long SerialVersionUID = 1L;

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
    private Long foodCardNumber;
    private double foodCardBalance;

    private Long fuelCardNumber;
    private double fuelCardBalance;

    private Long drugstoreNumber;
    private double drugstoreCardBalance;

    public Consumer(Integer id, String name, int documentNumber, Date birthDate, int mobilePhoneNumber,
                    int residencePhoneNumber, int phoneNumber, String email, String street, int number,
                    String city, String country, int portalCode, Long foodCardNumber, Long fuelCardNumber,
                    Long drugstoreNumber) {
        this.id = id;
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
        this.fuelCardNumber = fuelCardNumber;
        this.drugstoreNumber = drugstoreNumber;
    }
}
