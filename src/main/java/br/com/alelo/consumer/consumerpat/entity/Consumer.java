package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String name;
	int documentNumber;
	Date birthDate;

	// contacts
	@OneToOne(cascade = CascadeType.ALL)
	Contact contact;
	// Address
	@OneToOne(cascade = CascadeType.ALL)
	Address address;

	// cards
	@OneToOne(cascade = CascadeType.ALL)
	FoodCard food;
	@OneToOne(cascade = CascadeType.ALL)
	FuelCard fuel;
	@OneToOne(cascade = CascadeType.ALL)
	DrugstoreCard drugstore;

	public Consumer() {

	}

	Consumer(String name, int documentNumber, Date birthDate, Contact contact, Address address, FoodCard food,
			FuelCard fuel, DrugstoreCard drugstore) {
		this.name = name;
		this.documentNumber = documentNumber;
		this.birthDate = birthDate;
		this.contact = contact;
		this.address = address;
		this.food = food;
		this.fuel = fuel;
		this.drugstore = drugstore;
	}
}
