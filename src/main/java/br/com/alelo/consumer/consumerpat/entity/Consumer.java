package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Data
@Entity
@EqualsAndHashCode
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int documentNumber;
    private Date birthDate;

    //contacts
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    //Address
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    //cards
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "consumer_id")
    private Set<Card> cardList;


}
