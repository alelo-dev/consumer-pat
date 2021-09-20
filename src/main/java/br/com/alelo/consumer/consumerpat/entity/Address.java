package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

	private int number;
	private String street;
	private String city;
	private String country;
	private int portalCode;
	
}
