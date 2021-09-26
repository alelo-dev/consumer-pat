package br.com.alelo.consumer.consumerpat.service.dto.response;

import lombok.Data;

@Data
public class ContactResponse {

	private Long id;
	
	private int mobilePhoneNumber;

	private int residencePhoneNumber;

	private int phoneNumber;

	private String email;
}
