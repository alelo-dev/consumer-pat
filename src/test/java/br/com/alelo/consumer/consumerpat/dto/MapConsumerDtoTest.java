package br.com.alelo.consumer.consumerpat.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.alelo.consumer.consumerpat.emun.TypeCard;
import br.com.alelo.consumer.consumerpat.model.Address;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Contact;

class MapConsumerDtoTest {

	private List<CardDto> buildCadDtos() {
		List<CardDto> cards = new ArrayList<>();
		cards.add(new CardDto(TypeCard.DRUG, 10L));
		cards.add(new CardDto(TypeCard.FOOD, 20L));
		cards.add(new CardDto(TypeCard.FUEL, 30L));
		return cards;
	}

	private AddressDto buildAddresDto() {
		return AddressDto.builder()//
				.city("São Paulo")//
				.country("Brasil")//
				.number(10)//
				.portalCode(87600000)//
				.street("Av Brasil")//
				.build();
	}

	private ContactDto buildContactDto() {
		return ContactDto.builder()//
				.email("jgecen@gmail.com")//
				.mobilePhoneNumber(99998888L)//
				.residencePhoneNumber(88889999L)//
				.phoneNumber(33335555L)//
				.build();
	}

	
	@Test
	void testCreate() {
		ContactDto contactDto = buildContactDto();
		
		AddressDto addressDto = buildAddresDto();
		
		List<CardDto> cards = buildCadDtos();		
		
		ConsumerDto consumerDto = ConsumerDto.builder()//
				.documentNumber(1L)//
				.name("Fulano")//
				.contact(contactDto)//
				.address(addressDto)//
				.cards(cards)//
				.build();
		MapConsumerDto mapConsumerDto = new MapConsumerDto();
		
		Consumer consumer = mapConsumerDto.create(consumerDto);
		assertEquals(1L, consumer.getDocumentNumber());
		assertEquals("Fulano", consumer.getName());
		assertEquals("jgecen@gmail.com", consumer.getContact().getEmail());
		assertEquals("São Paulo", consumer.getAddress().getCity());
		assertEquals(3, consumer.getCards().size());
		
	}


	@Test
	void testUpdate() {
		ContactDto contactDto = buildContactDto();
		
		AddressDto addressDto = buildAddresDto();	
		
		ConsumerDto consumerDto = ConsumerDto.builder()//
				.documentNumber(1L)//
				.name("Fulano")//
				.contact(contactDto)//
				.address(addressDto)//
				.build();
		MapConsumerDto mapConsumerDto = new MapConsumerDto();
		
		Consumer consumer = Consumer.builder()//
				.address(new Address())
				.contact(new Contact())
				.build();
		mapConsumerDto.update(consumerDto, consumer);
		
		assertEquals(1L, consumer.getDocumentNumber());
		assertEquals("Fulano", consumer.getName());
		assertEquals("jgecen@gmail.com", consumer.getContact().getEmail());
		assertEquals("São Paulo", consumer.getAddress().getCity());

	}

}
