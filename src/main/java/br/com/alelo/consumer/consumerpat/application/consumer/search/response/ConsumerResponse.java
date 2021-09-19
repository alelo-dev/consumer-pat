package br.com.alelo.consumer.consumerpat.application.consumer.search.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumerResponse {

	private Long id;
	private String name;
	private String documentNumber;
	private Date birthDate;

	// contacts
	private String mobilePhoneNumber;
	private String residencePhoneNumber;
	private String phoneNumber;
	private String email;

	// Address
	private String addressStreet;
	private String addressNumber;
	private String addressCity;
	private String addressCountry;
	private String addressPostalCode;

}
