package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import br.com.alelo.consumer.consumerpat.entity.Address;
import lombok.Data;

@Data
public class AddressDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String street;
    private int number;
    private String city;
    private String country;
    private String postalCode;
    
    public Address toAddress() {
    	Address address = new Address();
    	address.setId(this.getId());
    	address.setStreet(this.getStreet());
    	address.setNumber(this.getNumber());
    	address.setCity(this.getCity());
    	address.setCountry(this.getCountry());
    	address.setPostalCode(this.getPostalCode());
    	
    	return address;
    }
    
    public static AddressDto fromAddress(Address address) {
    	if (address != null) {
    		AddressDto dto = new AddressDto();
    		dto.setId(address.getId());
    		dto.setStreet(address.getStreet());
    		dto.setNumber(address.getNumber());
    		dto.setCity(address.getCity());
    		dto.setCountry(address.getCountry());
    		dto.setPostalCode(address.getPostalCode());
    		
    		return dto;
    	}
    	return null;
    }
}
