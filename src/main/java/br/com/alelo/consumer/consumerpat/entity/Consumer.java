package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private int documentNumber;
	private Date birthDate;

	// contacts
	private int mobilePhoneNumber;
	private int residencePhoneNumber;
	private int phoneNumber;
	private String email;

	// Address
	private String street;
	private int number;
	private String city;
	private String country;
	private int portalCode;

	// cards
	private int foodCardNumber;
	private double foodCardBalance;

	private int fuelCardNumber;
	private double fuelCardBalance;

	private int drugstoreNumber;
	private double drugstoreCardBalance;

	//Inicios de Metodos Get e Set
	public Integer getId() {
		return id;
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

	// Get contacts
	public int getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public int getResidencePhoneNumber() {
		return residencePhoneNumber;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	// Set contacts
	public void setMobilePhoneNumber(int mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public void setResidencePhoneNumber(int residencePhoneNumber) {
		this.residencePhoneNumber = residencePhoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Get Address
	public String getStreet() {
		return street;
	}

	public int getNumber() {
		return number;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public int getPortalCode() {
		return portalCode;
	}

	// Set Address
	public void setStreet(String street) {
		this.street = street;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPortalCode(int portalCode) {
		this.portalCode = portalCode;
	}

	// Get Cards
	public int getFoodCardNumber() {
		return foodCardNumber;
	}
	
	public double getFoodCardBalance() {
		return this.foodCardBalance;
	}

	public int getFuelCardNumber() {
		return fuelCardNumber;
	}
	
	public double getFuelCardBalance() {
		return this.fuelCardBalance;
	}

	public int getDrugstoreNumber() {
		return drugstoreNumber;
	}
	
	public double getDrugstoreCardBalance() {
		return this.drugstoreCardBalance;
	}

	// Set Cards
	public void setFoodCardNumber(int foodCardNumber) {
		this.foodCardNumber = foodCardNumber;
	}

	public void setFoodCardBalance(double foodCardBalance) {
		this.foodCardBalance = foodCardBalance;
	}

	public void setFuelCardNumber(int fuelCardNumber) {
		this.fuelCardNumber = fuelCardNumber;
	}
	
	public void setFuelCardBalance(double fuelCardBalance) {
		this.fuelCardBalance = fuelCardBalance;
	}

	public void setDrugstoreNumber(int drugstoreNumber) {
		this.drugstoreNumber = drugstoreNumber;
	}
	
	public void setDrugstoreCardBalance(double drugstoreCardBalance) {
		this.drugstoreCardBalance = drugstoreCardBalance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Consumer consumer = (Consumer) o;
		return getDocumentNumber() == consumer.getDocumentNumber()
				&& getMobilePhoneNumber() == consumer.getMobilePhoneNumber()
				&& getResidencePhoneNumber() == consumer.getResidencePhoneNumber()
				&& getPhoneNumber() == consumer.getPhoneNumber() && getNumber() == consumer.getNumber()
				&& getPortalCode() == consumer.getPortalCode() && getFoodCardNumber() == consumer.getFoodCardNumber()
				&& Double.compare(consumer.foodCardBalance, foodCardBalance) == 0
				&& getFuelCardNumber() == consumer.getFuelCardNumber()
				&& Double.compare(consumer.fuelCardBalance, fuelCardBalance) == 0
				&& getDrugstoreNumber() == consumer.getDrugstoreNumber()
				&& Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0
				&& Objects.equals(getId(), consumer.getId()) && Objects.equals(getName(), consumer.getName())
				&& Objects.equals(getBirthDate(), consumer.getBirthDate())
				&& Objects.equals(getEmail(), consumer.getEmail()) && Objects.equals(getStreet(), consumer.getStreet())
				&& Objects.equals(getCity(), consumer.getCity()) && Objects.equals(getCountry(), consumer.getCountry());
	}

}
