package br.com.alelo.consumer.consumerpat.converter;

import java.time.LocalDateTime;

import br.com.alelo.consumer.consumerpat.dto.AddressRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.AddressResponseDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerContactResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Contact;

public class AddressConverter {
	
	public static Address toEntity(AddressRequestDTO addressRequestDTO) {

		Address address = new Address();

		if(addressRequestDTO != null) {
			address.setId(addressRequestDTO.getId());
			address.setAddressStreet(addressRequestDTO.getAddressStreet());
			address.setAddressNumber(addressRequestDTO.getAddressNumber());
			address.setCity(addressRequestDTO.getCity());
			address.setCountry(addressRequestDTO.getCountry());
			address.setPostalCode(addressRequestDTO.getPostalCode());
			address.setCreatedAt(LocalDateTime.now());
		}

		return address;
	}

	public static AddressResponseDTO toDTO(Address address) {

		AddressResponseDTO addressResponseDTO = new AddressResponseDTO();

		if(addressResponseDTO != null) {
			addressResponseDTO.setId(address.getId());
			addressResponseDTO.setAddressStreet(address.getAddressStreet());
			addressResponseDTO.setAddressNumber(address.getAddressNumber());
			addressResponseDTO.setCity(address.getCity());
			addressResponseDTO.setCountry(address.getCountry());
			addressResponseDTO.setPostalCode(address.getPostalCode());
		}

		return addressResponseDTO;
	}
	
	public static Address updateTarget(final Address target, final AddressRequestDTO origin) {
		
		
		if(origin != null) {
			target.setId(origin.getId());
			target.setAddressStreet(origin.getAddressStreet());
			target.setAddressNumber(origin.getAddressNumber());
			target.setCity(origin.getCity());
			target.setCountry(origin.getCountry());
			target.setPostalCode(origin.getPostalCode());
			target.setCreatedAt(LocalDateTime.now());
		}

		return target;
		
	}
}
