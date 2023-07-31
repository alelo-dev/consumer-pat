package br.com.alelo.consumer.consumerpat.domain.consumer.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Address {
    @NotBlank(message = "street is required.")
    private String street;
    @NotNull(message = "number is required.")
    private Integer number;
    @NotBlank(message = "city is required.")
    private String city;
    @NotBlank(message = "country is required.")
    private String country;
    @NotBlank(message = "portalCode is required.")
    private String portalCode;

    public Address(String street, Integer number, String city, String country, String portalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.portalCode = portalCode;
    }

}
