package br.com.alelo.consumer.consumerpat.application.consumer.create.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateConsumerRequest {

	private String name;
	private String documentNumber;
	private LocalDate birthDate;

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
