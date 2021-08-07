package br.com.alelo.consumer.consumerpat.mock.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alelo.consumer.consumerpat.dto.AddressCreateDTO;
import br.com.alelo.consumer.consumerpat.enuns.AddressTypeEnum;

public class AddressCreateDTOMock {

	private AddressCreateDTOMock() {}
	
	public static AddressCreateDTO createHome() {
		return AddressCreateDTO.builder()
				.type(AddressTypeEnum.HOME)
				.number(200)
				.street("Rua 1")
				.district("Centro")
				.city("Goiânia")
				.country("Brasil")
				.postalCode("74.000-001")
				.build();
	}
	
	public static AddressCreateDTO createBussiness() {
		return AddressCreateDTO.builder()
				.type(AddressTypeEnum.BUSSINESS)
				.number(1001)
				.street("Rua 99")
				.district("Sul")
				.city("Goiânia")
				.country("Brasil")
				.postalCode("74.999-001")
				.build();
	}
	
	public static List<AddressCreateDTO> list() {
		List<AddressCreateDTO> list = new ArrayList<AddressCreateDTO>();
		list.add(createHome());
		list.add(createBussiness());
		return list;
	}
}
