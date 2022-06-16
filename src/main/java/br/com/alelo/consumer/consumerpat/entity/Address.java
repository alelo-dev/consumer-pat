package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String street;
	int number;
	String city;
	String country;
	int portalCode;
	@OneToOne
	private Consumer consumer;

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String street, int number, String city, String country, int portalCode) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
		this.portalCode = portalCode;
	}

}
