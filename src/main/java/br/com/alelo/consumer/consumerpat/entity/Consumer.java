package br.com.alelo.consumer.consumerpat.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    @NotNull
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    int portalCode;

    //cards
    @Transient
    long foodCardNumber;
    @Transient
    double foodCardBalance;

    @Transient
    long fuelCardNumber;
    @Transient
    double fuelCardBalance;
    @Transient
    long drugstoreNumber;
    @Transient
    double drugstoreCardBalance;

}
