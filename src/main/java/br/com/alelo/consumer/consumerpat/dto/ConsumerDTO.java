package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;

public class ConsumerDTO {

	
	Integer id;
    String name;
    Long documentNumber;
    Date birthDate;

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
    Double foodCardBalance;

    Long fuelCardNumber;
    Double fuelCardBalance;

    int drugstoreNumber;
    Double drugstoreCardBalance;
	
    
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
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
	public Double getFoodCardBalance() {
		return foodCardBalance;
	}
	public void setFoodCardBalance(Double foodCardBalance) {
		this.foodCardBalance = foodCardBalance;
	}
	public Long getFuelCardNumber() {
		return fuelCardNumber;
	}
	public void setFuelCardNumber(Long fuelCardNumber) {
		this.fuelCardNumber = fuelCardNumber;
	}
	public Double getFuelCardBalance() {
		return fuelCardBalance;
	}
	public void setFuelCardBalance(Double fuelCardBalance) {
		this.fuelCardBalance = fuelCardBalance;
	}
	public int getDrugstoreNumber() {
		return drugstoreNumber;
	}
	public void setDrugstoreNumber(int drugstoreNumber) {
		this.drugstoreNumber = drugstoreNumber;
	}
	public Double getDrugstoreCardBalance() {
		return drugstoreCardBalance;
	}
	public void setDrugstoreCardBalance(Double drugstoreCardBalance) {
		this.drugstoreCardBalance = drugstoreCardBalance;
	}
	
}
