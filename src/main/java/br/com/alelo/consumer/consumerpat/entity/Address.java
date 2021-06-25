package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Embeddable
public class Address {

	private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;
}
