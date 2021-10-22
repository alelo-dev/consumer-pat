package br.com.alelo.consumer.consumerpat.domain.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Address {

    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;
}
