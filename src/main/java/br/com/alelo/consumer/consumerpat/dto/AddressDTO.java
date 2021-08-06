package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.enuns.AddressTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	private Long id;
	private AddressTypeEnum type;
	private String street;
    private Integer number;
    private String district;
    private String city;
    private String country;
    private String postalCode;
    
    public static AddressDTO to(Address address) {
    	return new AddressDTO(
    				address.getId(), 
    				address.getType(),
    				address.getStreet(),
    				address.getNumber(),
    				address.getDistrict(),
    				address.getCity(),
    				address.getCountry(),
    				address.getPostalCode()
    			);
    }
    
    public Address toEntity() {
    	return new Address(id, type, street, number, district, city, country, postalCode, null);
    }
}
