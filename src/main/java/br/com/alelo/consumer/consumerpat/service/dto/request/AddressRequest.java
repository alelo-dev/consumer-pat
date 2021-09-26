package br.com.alelo.consumer.consumerpat.service.dto.request;

import lombok.Data;

@Data
public class AddressRequest {
	
	private Long id;
	
	private String street;
	
    private int number;
    
    private String city;
    
    private String country;
    
    private  int portalCode;
}
