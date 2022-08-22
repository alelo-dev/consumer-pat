package br.com.alelo.consumer.consumerpat.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

    private String street;
    
    private int number;
    
    private String city;
    
    private String country;
    
    private int portalCode;

}
