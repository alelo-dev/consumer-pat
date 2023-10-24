package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name="consumer")
public class Consumer extends BaseEntity {
    
	@Column
	public String name;
	
	@Column
    public int documentNumber;
    
	@Column
    public Date birthDate;

    //contacts
	@Column
    public int mobilePhoneNumber;
	
	@Column
    public int residencePhoneNumber;
	
	@Column
    public int phoneNumber;
	
	@Column
    public String email;

    //Address
	@Column
    public String street;
	
	@Column
    public int number;
	
	@Column
    public String city;
	
	@Column
    public String country;
	
	@Column
    public int portalCode;

    //cards
	@Column
    public int foodCardNumber;
	
	@Column
    public double foodCardBalance;

	@Column
    public int fuelCardNumber;
    
    @Column
    public double fuelCardBalance;

    @Column
    public int drugstoreNumber;
    
    @Column
    public double drugstoreCardBalance;
    
        
    @Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
		return id;
	}
  
    // Mantendo o get para que o banco seja criado
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

	@Override
    public boolean equals(Object consumerObject) {
        if (this == consumerObject) return true;
        if (consumerObject == null || getClass() != consumerObject.getClass()) return false;
        Consumer consumer = (Consumer) consumerObject;
        return documentNumber == consumer.documentNumber
            && mobilePhoneNumber == consumer.mobilePhoneNumber
            && residencePhoneNumber == consumer.residencePhoneNumber
            && phoneNumber == consumer.phoneNumber
            && number == consumer.number
            && portalCode == consumer.portalCode
            && foodCardNumber == consumer.foodCardNumber
            && Double.compare(consumer.foodCardBalance, foodCardBalance) == INITIAL_VALUE_ZERO
            && fuelCardNumber == consumer.fuelCardNumber && Double.compare(consumer.fuelCardBalance, fuelCardBalance) == INITIAL_VALUE_ZERO
            && drugstoreNumber == consumer.drugstoreNumber && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == INITIAL_VALUE_ZERO
            && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
            && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
            && Objects.equals(country, consumer.country);
    }

}
