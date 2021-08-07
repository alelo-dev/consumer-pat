package br.com.alelo.consumer.consumerpat.mock.dto;

import java.time.LocalDate;

import br.com.alelo.consumer.consumerpat.dto.ConsumerCreateDTO;

public class ConsumerCreateDTOMock {

	private ConsumerCreateDTOMock() {}
	
	public static ConsumerCreateDTO create() {
		return ConsumerCreateDTO.builder()
				.name("Joao")
				.documentNumber(112233)
				.birthDate(LocalDate.of(1992, 12 , 31))
				.contacts(ContactCreateDTOMock.list())
				.cards(CardCreateDTOMock.list())
				.address(AddressCreateDTOMock.list())
				.build();
	}
}
