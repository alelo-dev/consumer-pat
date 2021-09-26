package br.com.alelo.consumer.consumerpat.service.dto.request;

import lombok.Data;

@Data
public class ContactRequest {
	
	private Long id;
	
	private int mobilePhoneNumber;
	
    private int residencePhoneNumber;
    
    private int phoneNumber;
    
    private String email;
}
