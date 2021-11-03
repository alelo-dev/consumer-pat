package br.com.alelo.consumer.consumerpat.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de domínio representando um endereço. 
 * Nomes das tabelas no BD em 'plural'.
 * 
 */
@Entity
@Table(name = "addresses")
public final class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private final int postalCode;

	private final String street;

	private final int number;

	private final String city;

	private final String state;

	private final String country;

	public Address() {
		this.postalCode = 0;
		this.street = null;
		this.number = 0;
		this.city = null;
		this.state = null;
		this.country = null;
	}
	
	public Address(int postalCode, String street, int number, String city, String state, String country) {
		this.postalCode = postalCode;
		this.street = street;
		this.number = number;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	public long getId() {
		return id;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public String getStreet() {
		return street;
	}

	public int getNumber() {
		return number;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, number, postalCode, state, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Address address = (Address) obj;
		
		return Objects.equals(city, address.city) 
				&& Objects.equals(country, address.country) 
				&& number == address.number 
				&& postalCode == address.postalCode 
				&& Objects.equals(state, address.state)
				&& Objects.equals(street, address.street);
	}
}