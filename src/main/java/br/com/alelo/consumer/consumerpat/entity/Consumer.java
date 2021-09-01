package br.com.alelo.consumer.consumerpat.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;
    public int documentNumber;
    public Date birthDate;

    //contacts
    public int mobilePhoneNumber;
    public int residencePhoneNumber;
    public int phoneNumber;
    public String email;

    //Address
    public String street;
    public int number;
    public String city;
    public String country;
    public int portalCode;

    //cards
    public int foodCardNumber;
    public double foodCardBalance;

    public int fuelCardNumber;
    public double fuelCardBalance;

    public int drugstoreNumber;
    public double drugstoreCardBalance;

}
