package br.com.alelo.consumer.consumerpat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private long addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "city")
    private int number;

    @Column(name = "city")
    private String country;

    @Column(name = "city")
    private int portalCode;
//    String city;
//    String country;
//    int portalCode;

    @OneToOne(mappedBy = "address")
    private Consumer consumer;
}
