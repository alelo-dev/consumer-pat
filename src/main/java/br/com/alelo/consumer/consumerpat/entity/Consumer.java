package br.com.alelo.consumer.consumerpat.entity;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
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
    @Column(unique = true)
    int foodCardNumber;
    double foodCardBalance;

    @Column(unique = true)
    int fuelCardNumber;
    double fuelCardBalance;

    @Column(unique = true)
    int drugstoreNumber;
    double drugstoreCardBalance;

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + documentNumber;
		long temp;
		temp = Double.doubleToLongBits(drugstoreCardBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + drugstoreNumber;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		temp = Double.doubleToLongBits(foodCardBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + foodCardNumber;
		temp = Double.doubleToLongBits(fuelCardBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + fuelCardNumber;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + mobilePhoneNumber;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + phoneNumber;
		result = prime * result + portalCode;
		result = prime * result + residencePhoneNumber;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
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
                && drugstoreNumber == consumer.drugstoreNumber && Double.compare(consumer.drugstoreCardBalance, drugstoreCardBalance) == 0
                && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email) && Objects.equals(street, consumer.street) && Objects.equals(city, consumer.city)
                && Objects.equals(country, consumer.country);
    }
}
