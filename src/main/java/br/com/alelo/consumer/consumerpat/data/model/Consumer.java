package br.com.alelo.consumer.consumerpat.data.model;


import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
public class Consumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "NAME", nullable = false)
    String name;

    @Column(name = "DUCUMENT_NUMBER", nullable = false, length = 14)
    String documentNumber;

    @Column(name = "BIRTH_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    Date birthDate;

    //contacts
    Integer mobilePhoneNumber;
    Integer residencePhoneNumber;
    Integer phoneNumber;
    String email;

    @OneToOne
    @JoinColumn(name = "ID_CONSUMER")
    Address address;

    //cards
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CONSUMER")
    List<Card> cards;

}
