package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {

    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;

    public AddressDTO() {
    }

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.country = address.getCountry();
        this.portalCode = address.getPortalCode();
    }
}
