package br.com.alelo.consumer.consumerpat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressDto {

    private String street;
    
    private int number;
    
    private String city;
    
    private String country;
    
    private int portalCode;
}
