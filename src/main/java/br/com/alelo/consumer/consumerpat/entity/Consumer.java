package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Consumer extends BaseEntity {
    
	public String name;
    public int documentNumber;
    public Date birthDate;

    //contacts
    public int mobilePhoneNumber;
    public int residencePhoneNumber;
    public int phoneNumber;
    public String email;

    //Address
    public String street;
    public int number;
    public String city;
    public String country;
    public int portalCode;

    //cards
    public int foodCardNumber;
    public double foodCardBalance;

    public int fuelCardNumber;
    public double fuelCardBalance;

    public int drugstoreNumber;
    public double drugstoreCardBalance;
    
        
    @Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
		return id;
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
