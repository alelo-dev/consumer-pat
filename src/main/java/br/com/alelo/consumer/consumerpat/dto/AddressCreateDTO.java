package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.enuns.AddressTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateDTO {

	private AddressTypeEnum type;
	private String street;
    private Integer number;
    private String district;
    private String city;
    private String country;
    private String postalCode;
    
    public Address toEntity() {
    	return new Address(null, type, street, number, district, city, country, postalCode, null);
    }
	
}
