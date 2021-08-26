package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String street;
    int number;
    String city;
    String country;
    int portalCode;
    @ManyToOne
    private Consumer consumer;

    public Address(String street, int number, String city, String country, int portalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
    }

    protected Address(){

    }
}
