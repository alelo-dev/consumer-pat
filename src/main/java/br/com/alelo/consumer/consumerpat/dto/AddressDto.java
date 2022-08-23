package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String street;
    
    private int number;
    
    private String city;
    
    private String country;
    
    private int portalCode;
}
