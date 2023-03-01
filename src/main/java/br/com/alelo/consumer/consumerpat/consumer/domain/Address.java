package br.com.alelo.consumer.consumerpat.consumer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    private String streetName;

    private int number;

    private String city;

    private String country;

    private int postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;
}
