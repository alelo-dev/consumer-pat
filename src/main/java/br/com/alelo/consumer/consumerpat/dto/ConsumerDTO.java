package br.com.alelo.consumer.consumerpat.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConsumerDTO {

	
	Integer id;
    String name;
    Long documentNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate  birthDate;

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

    Long drugStoreNumber;
    Double drugStoreCardBalance;
	
    
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
	public Long getDrugStoreNumber() {
		return drugStoreNumber;
	}
	public void setDrugStoreNumber(Long drugstoreNumber) {
		this.drugStoreNumber = drugstoreNumber;
	}
	public Double getDrugStoreCardBalance() {
		return drugStoreCardBalance;
	}
	public void setDrugStoreCardBalance(Double drugStoreCardBalance) {
		this.drugStoreCardBalance = drugStoreCardBalance;
	}
	
}
