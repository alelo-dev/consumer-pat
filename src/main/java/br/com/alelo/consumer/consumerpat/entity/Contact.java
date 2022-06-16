package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	int mobilePhoneNumber;
	int residencePhoneNumber;
	int phoneNumber;
	String email;
	@OneToOne
	private Consumer consumer;

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public Contact(int mobilePhoneNumber, int residencePhoneNumber, int phoneNumber, String email) {
		super();
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.residencePhoneNumber = residencePhoneNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

}
