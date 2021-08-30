package br.com.alelo.consumer.consumerpat.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import br.com.alelo.consumer.consumerpat.domain.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ContactDTO;
import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import br.com.alelo.consumer.consumerpat.domain.response.CardResponse;
import br.com.alelo.consumer.consumerpat.domain.response.ConsumerResponse;

public class ResponseMocks {
	
	public static ConsumerResponse consumerResponse(CardResponse... cardPayloads) {
		final var payload = new ConsumerResponse();
		payload.setName("A Consumer");
		payload.setDocumentNumber("72542961026");
		payload.setBirthDate(LocalDate.parse("1980-01-01"));
		
		final var address = new AddressDTO();
		address.setStreet("A Street");
		address.setNumber("1056");
		address.setPostalCode("0010578");
		address.setCity("A City");
		address.setCountry("Brazil");
		payload.setAddress(address);
		
		final var contact = new ContactDTO();
		contact.setEmail("a.consumer@email");
		contact.setMobilePhoneNumber("+5511981234567");
		contact.setPhoneNumber("+551125503322");
		contact.setResidencePhoneNumber("+551125513456");
		payload.setContact(contact);
		
		final var cards = new LinkedHashSet<CardResponse>();
		Stream.of(cardPayloads).forEach(cards::add);
		payload.setCards(cards);
		
		return payload;
	}
	
	public static CardResponse cardResponse(String cardNumber, CardType cardType) {
		final var card = new CardResponse();
		card.setNumber(cardNumber);
		card.setType(cardType);
		card.setBalance(BigDecimal.TEN);
		return card;
	}

}
