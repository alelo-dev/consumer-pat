package br.com.alelo.consumer.consumerpat.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    int documentNumber;
    LocalDate birthDate;

    //contacts
    long mobilePhoneNumber;
    long residencePhoneNumber;
    long phoneNumber;
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
