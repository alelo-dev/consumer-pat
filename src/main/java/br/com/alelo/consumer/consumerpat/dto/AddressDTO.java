package br.com.alelo.consumer.consumerpat.dto;

import javax.persistence.EntityManager;

public class AddressDTO {

    private String street;
    private int number;
    private String city;
    private String country;
    private String portalCode;

    public AddressDTO(String street, int number, String city, String country, String portalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
    }

    AddressDTO toModel(EntityManager manager) {
        return new AddressDTO(street, number, city, country, portalCode);
    }
}
