package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private String portalCode;

    //cards
    @OneToMany(mappedBy = "consumer")
    private List<Card> cards;

}
