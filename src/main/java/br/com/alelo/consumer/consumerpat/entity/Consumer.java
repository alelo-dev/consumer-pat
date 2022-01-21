package br.com.alelo.consumer.consumerpat.entity;


import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Consumer {



	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    Long documentNumber;

    LocalDate birthDate;

    //contacts
    long mobilePhoneNumber;
    long residencePhoneNumber;
    long phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    int portalCode;

    //cards
    Long foodCardNumber;
    double foodCardBalance;

    Long fuelCardNumber;
    double fuelCardBalance;

    Long drugStoreNumber;
    double drugStoreCardBalance;

    
    
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



	public Long getDocumentNumber() {
		return documentNumber;
	}



	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}



	public LocalDate getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}



	public long getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}



	public void setMobilePhoneNumber(long mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}



	public long getResidencePhoneNumber() {
		return residencePhoneNumber;
	}



	public void setResidencePhoneNumber(long residencePhoneNumber) {
		this.residencePhoneNumber = residencePhoneNumber;
	}



	public long getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(long phoneNumber) {
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



	public Long getFoodCardNumber() {
		return foodCardNumber;
	}



	public void setFoodCardNumber(Long foodCardNumber) {
		this.foodCardNumber = foodCardNumber;
	}



	public double getFoodCardBalance() {
		return foodCardBalance;
	}



	public void setFoodCardBalance(double foodCardBalance) {
		this.foodCardBalance = foodCardBalance;
	}



	public Long getFuelCardNumber() {
		return fuelCardNumber;
	}



	public void setFuelCardNumber(Long fuelCardNumber) {
		this.fuelCardNumber = fuelCardNumber;
	}



	public double getFuelCardBalance() {
		return fuelCardBalance;
	}



	public void setFuelCardBalance(double fuelCardBalance) {
		this.fuelCardBalance = fuelCardBalance;
	}





	public Long getDrugStoreNumber() {
		return drugStoreNumber;
	}



	public void setDrugStoreNumber(Long drugStoreNumber) {
		this.drugStoreNumber = drugStoreNumber;
	}



	public double getDrugStoreCardBalance() {
		return drugStoreCardBalance;
	}



	public void setDrugStoreCardBalance(double drugStoreCardBalance) {
		this.drugStoreCardBalance = drugStoreCardBalance;
	}



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
                && drugStoreNumber == consumer.drugStoreNumber && Double.compare(consumer.drugStoreCardBalance, drugStoreCardBalance) == 0
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country);
    }


}
