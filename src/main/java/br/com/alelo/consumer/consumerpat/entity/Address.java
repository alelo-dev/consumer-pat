package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

    //Address
    private String street;
    private int number;
    private String city;
    private String country;
    private  int portalCode;	
}
