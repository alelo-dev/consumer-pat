package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Contacts {

	private int mobilePhoneNumber;
	private int residencePhoneNumber;
	private int phoneNumber;
	private String email;

}
