package br.com.alelo.consumer.consumerpat.entity;


import jdk.jfr.DataAmount;
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
    private Integer id;
    private String name;
    private int documentNumber;
    private Date birthDate;

    //contacts
    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;

    //Address
    private  String street;
    private  int number;
    private  String city;
    private  String country;
    private  int portalCode;

    //cards
    private  int foodCardNumber;
    private  double foodCardBalance;

    private  int fuelCardNumber;
    private  double fuelCardBalance;

    private  int drugstoreNumber;
    private  double drugstoreCardBalance;
    
    
    

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




	public int getFoodCardNumber() {
		return foodCardNumber;
	}




	public void setFoodCardNumber(int foodCardNumber) {
		this.foodCardNumber = foodCardNumber;
	}




	public double getFoodCardBalance() {
		return foodCardBalance;
	}




	public void setFoodCardBalance(double foodCardBalance) {
		this.foodCardBalance = foodCardBalance;
	}




	public int getFuelCardNumber() {
		return fuelCardNumber;
	}




	public void setFuelCardNumber(int fuelCardNumber) {
		this.fuelCardNumber = fuelCardNumber;
	}




	public double getFuelCardBalance() {
		return fuelCardBalance;
	}




	public void setFuelCardBalance(double fuelCardBalance) {
		this.fuelCardBalance = fuelCardBalance;
	}




	public int getDrugstoreNumber() {
		return drugstoreNumber;
	}




	public void setDrugstoreNumber(int drugstoreNumber) {
		this.drugstoreNumber = drugstoreNumber;
	}




	public double getDrugstoreCardBalance() {
		return drugstoreCardBalance;
	}




	public void setDrugstoreCardBalance(double drugstoreCardBalance) {
		this.drugstoreCardBalance = drugstoreCardBalance;
	}





}
