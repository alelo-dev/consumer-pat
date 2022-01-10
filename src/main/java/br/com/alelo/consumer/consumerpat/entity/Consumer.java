package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "O campo nao pode ser nulo")
    String name;
    Long documentNumber;

    @NotEmpty(message = "O campo nao pode ser nulo")
    Date birthDate;

    //contacts
    Long mobilePhoneNumber;
    Long residencePhoneNumber;
    Long phoneNumber;

    String email;

    //Address
    String street;
    Long number;
    String city;
    String country;
    Long portalCode;

    //cards

    @NotNull(message = "O campo nao pode ser nulo")
    Long foodCardNumber;

    @NotNull(message = "O campo nao pode ser nulo")
    Long foodCardBalance;

    @NotNull(message = "O campo nao pode ser nulo")
    Long fuelCardNumber;

    @NotNull(message = "O campo nao pode ser nulo")
    Long fuelCardBalance;


    @NotNull(message = "O campo nao pode ser nulo")
    Long drugstoreNumber;

    @NotNull(message = "O campo nao pode ser nulo")
    Long drugstoreCardBalance;

}
