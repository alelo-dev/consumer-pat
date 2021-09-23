package br.com.alelo.consumer.consumerpat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String name;
	int documentNumber;
	Date birthDate;

	// contacts
	int mobilePhoneNumber;
	int residencePhoneNumber;
	int phoneNumber;
	String email;

	// Address
	String street;
	int number;
	String city;
	String country;
	int portalCode;
	
	@OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
	List<Card> cards = new ArrayList<>();



	public Consumer() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(int documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(int mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public int getResidencePhoneNumber() {
		return residencePhoneNumber;
	}

	public void setResidencePhoneNumber(int residencePhoneNumber) {
		this.residencePhoneNumber = residencePhoneNumber;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPortalCode() {
		return portalCode;
	}

	public void setPortalCode(int portalCode) {
		this.portalCode = portalCode;
	}
	

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Consumer consumer = (Consumer) o;
		return documentNumber == consumer.documentNumber && mobilePhoneNumber == consumer.mobilePhoneNumber
				&& residencePhoneNumber == consumer.residencePhoneNumber && phoneNumber == consumer.phoneNumber
				&& number == consumer.number && portalCode == consumer.portalCode
				&& Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name)
				&& Objects.equals(birthDate, consumer.birthDate) && Objects.equals(email, consumer.email)
				&& Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
				&& Objects.equals(country, consumer.country);
	}

}
