package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.dto.AddressDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;

    public Address() {
    }

    public Address(AddressDTO address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.country = address.getCountry();
        this.portalCode = address.getPortalCode();
    }
}
