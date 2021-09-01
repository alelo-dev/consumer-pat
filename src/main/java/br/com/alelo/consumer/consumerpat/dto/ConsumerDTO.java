package br.com.alelo.consumer.consumerpat.dto;

import java.util.Date;

public class ConsumerDTO {

	private Integer id;
	private String name;
	private int documentNumber;
	private Date birthDate;

	// contacts
	private Long mobilePhoneNumber;
	private Long residencePhoneNumber;
	private Long phoneNumber;
	private String email;

	// Address
	private String street;
	private int number;
	private String city;
	private String country;
	private int portalCode;

	// cards
	private Long foodCardNumber;
	private double foodCardBalance;

	private Long fuelCardNumber;
	private double fuelCardBalance;

	private Long drugstoreNumber;
	private double drugstoreCardBalance;

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

	public Long getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(Long mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public Long getResidencePhoneNumber() {
		return residencePhoneNumber;
	}

	public void setResidencePhoneNumber(Long residencePhoneNumber) {
		this.residencePhoneNumber = residencePhoneNumber;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
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

	public Long getDrugstoreNumber() {
		return drugstoreNumber;
	}

	public void setDrugstoreNumber(Long drugstoreNumber) {
		this.drugstoreNumber = drugstoreNumber;
	}

	public double getDrugstoreCardBalance() {
		return drugstoreCardBalance;
	}

	public void setDrugstoreCardBalance(double drugstoreCardBalance) {
		this.drugstoreCardBalance = drugstoreCardBalance;
	}

}
