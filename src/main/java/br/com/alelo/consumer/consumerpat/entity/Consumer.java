package br.com.alelo.consumer.consumerpat.entity;


import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String documentNumber;

    private Date birthDate;

    //contacts
    private String mobilePhoneNumber; //+55 11 98374-2978

    private String residencePhoneNumber;

    private String phoneNumber;

    private String email;

    //Address
    private String street;

    private String number;

    private String city;

    private String country;

    private String postalCode;

    //cards 4444 5555 66667777
    private Long foodCardNumber;

    private BigDecimal foodCardBalance;

    private Long fuelCardNumber;

    private BigDecimal fuelCardBalance;

    private String drugstoreNumber;

    private BigDecimal drugstoreCardBalance;


}
