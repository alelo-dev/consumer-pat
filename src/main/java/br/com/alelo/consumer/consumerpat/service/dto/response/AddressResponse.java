package br.com.alelo.consumer.consumerpat.service.dto.response;

import lombok.Data;

@Data
public class AddressResponse {
	
	private Long id;

	private String street;
	
    private int number;
    
    private String city;
    
    private String country;
    
    private  int portalCode;
}
