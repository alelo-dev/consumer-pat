package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * - Retirado o metodo equals pois na anotacao @Data ja contem o
 * EqualsAndHashCode 
 * - Retirado a importacao java.util.Objects
 * 
 * @author HLJunior
 *
 */

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
	int mobilePhoneNumber;
	int residencePhoneNumber;
	int phoneNumber;
	String email;

	// Address
	String street;
	int number;
	String city;
	String country;
	int portalCode;

	// cards
	int foodCardNumber;
	double foodCardBalance;

	int fuelCardNumber;
	double fuelCardBalance;

	int drugstoreNumber;
	double drugstoreCardBalance;

}
