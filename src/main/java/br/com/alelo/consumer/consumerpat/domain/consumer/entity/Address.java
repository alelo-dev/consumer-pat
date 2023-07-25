package br.com.alelo.consumer.consumerpat.domain.consumer.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
public class Address {
    @NotBlank(message = "Street is required.")
    private String street;
    @NotNull(message = "Number is required.")
    private Integer number;
    @NotBlank(message = "City is required.")
    private String city;
    @NotBlank(message = "Country is required.")
    private String country;
    @NotBlank(message = "Portal code is required.")
    private String portalCode;

    public Address(String street, Integer number, String city, String country, String portalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
    }

}
