package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "mobilePhoneNumber")
	private int mobilePhoneNumber;

	@Column(name = "residencePhoneNumber")
	private int residencePhoneNumber;

	@Column(name = "phoneNumber")
	private int phoneNumber;

	@Column(name = "email")
	private String email;
}
