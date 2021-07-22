package br.com.alelo.consumer.consumerpat.dto;

import javax.persistence.EntityManager;

public class AddressDTO {

    private String street;
    private int number;
    private String city;
    private String country;
    private String postalCode;

    public AddressDTO(String street, int number, String city, String country, String postalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    AddressDTO toModel(EntityManager manager) {
        return new AddressDTO(street, number, city, country, postalCode);
    }
}
