package br.com.alelo.consumer.consumerpat.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    int documentNumber;
    Date birthDate;

    //contacts
    int mobilePhoneNumber;
    int residencePhoneNumber;
    int phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    int portalCode;

    //cards
    int foodCardNumber;
    double foodCardBalance;

    int fuelCardNumber;
    double fuelCardBalance;

    int drugstoreCardNumber;
    double drugstoreCardBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return documentNumber == consumer.documentNumber
                && mobilePhoneNumber == consumer.mobilePhoneNumber
                && residencePhoneNumber == consumer.residencePhoneNumber
                && phoneNumber == consumer.phoneNumber
                && number == consumer.number
                && portalCode == consumer.portalCode
                && foodCardNumber == consumer.foodCardNumber
                && Double.compare(consumer.foodCardBalance, foodCardBalance) == 0
                && fuelCardNumber == consumer.fuelCardNumber && Double.compare(consumer.fuelCardBalance, fuelCardBalance) == 0
                && drugstoreCardNumber == consumer.drugstoreCardNumber && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country);
    }

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the documentNumber
	 */
	public int getDocumentNumber() {
		return documentNumber;
	}

	/**
	 * @param documentNumber the documentNumber to set
	 */
	public void setDocumentNumber(int documentNumber) {
		this.documentNumber = documentNumber;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the mobilePhoneNumber
	 */
	public int getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * @param mobilePhoneNumber the mobilePhoneNumber to set
	 */
	public void setMobilePhoneNumber(int mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * @return the residencePhoneNumber
	 */
	public int getResidencePhoneNumber() {
		return residencePhoneNumber;
	}

	/**
	 * @param i the residencePhoneNumber to set
	 */
	public void setResidencePhoneNumber(int i) {
		this.residencePhoneNumber = i;
	}

	/**
	 * @return the phoneNumber
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the portalCode
	 */
	public int getPortalCode() {
		return portalCode;
	}

	/**
	 * @param portalCode the portalCode to set
	 */
	public void setPortalCode(int portalCode) {
		this.portalCode = portalCode;
	}

	/**
	 * @return the foodCardNumber
	 */
	public int getFoodCardNumber() {
		return foodCardNumber;
	}

	/**
	 * @param foodCardNumber the foodCardNumber to set
	 */
	public void setFoodCardNumber(int foodCardNumber) {
		this.foodCardNumber = foodCardNumber;
	}

	/**
	 * @return the foodCardBalance
	 */
	public double getFoodCardBalance() {
		return foodCardBalance;
	}

	/**
	 * @param foodCardBalance the foodCardBalance to set
	 */
	public void setFoodCardBalance(double foodCardBalance) {
		this.foodCardBalance = foodCardBalance;
	}

	/**
	 * @return the fuelCardNumber
	 */
	public int getFuelCardNumber() {
		return fuelCardNumber;
	}

	/**
	 * @param fuelCardNumber the fuelCardNumber to set
	 */
	public void setFuelCardNumber(int fuelCardNumber) {
		this.fuelCardNumber = fuelCardNumber;
	}

	/**
	 * @return the fuelCardBalance
	 */
	public double getFuelCardBalance() {
		return fuelCardBalance;
	}

	/**
	 * @param fuelCardBalance the fuelCardBalance to set
	 */
	public void setFuelCardBalance(double fuelCardBalance) {
		this.fuelCardBalance = fuelCardBalance;
	}

	/**
	 * @return the drugstoreCardNumber
	 */
	public int getDrugstoreCardNumber() {
		return drugstoreCardNumber;
	}

	/**
	 * @param drugstoreCardNumber the drugstoreCardNumber to set
	 */
	public void setDrugstoreCardNumber(int drugstoreCardNumber) {
		this.drugstoreCardNumber = drugstoreCardNumber;
	}

	/**
	 * @return the drugstoreCardBalance
	 */
	public double getDrugstoreCardBalance() {
		return drugstoreCardBalance;
	}

	/**
	 * @param drugstoreCardBalance the drugstoreCardBalance to set
	 */
	public void setDrugstoreCardBalance(double drugstoreCardBalance) {
		this.drugstoreCardBalance = drugstoreCardBalance;
	}


}
