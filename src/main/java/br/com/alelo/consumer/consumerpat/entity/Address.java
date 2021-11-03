package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
    String street;
    String number;
    String city;
    String country;
    String postalCode;
}
