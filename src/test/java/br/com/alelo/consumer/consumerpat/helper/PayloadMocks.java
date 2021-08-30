package br.com.alelo.consumer.consumerpat.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import br.com.alelo.consumer.consumerpat.domain.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ContactDTO;
import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import br.com.alelo.consumer.consumerpat.domain.payload.BuyPayload;
import br.com.alelo.consumer.consumerpat.domain.payload.CardPayload;
import br.com.alelo.consumer.consumerpat.domain.payload.ConsumerPayload;

public final class PayloadMocks {

	public static BuyPayload buyPayload(String cardNumber, CardType cardType) {
		final var payload = new BuyPayload();
		payload.setEstablishmentName("A Establishment");
		payload.setProductDescription("A Product");
		payload.setValue(BigDecimal.TEN);
		payload.setEstablishmentType(cardType);
		return payload;
	}
	
	public static ConsumerPayload consumerPayload(CardPayload... cardPayloads) {
		final var payload = new ConsumerPayload();
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
		
		final var cards = new LinkedHashSet<CardPayload>();
		Stream.of(cardPayloads).forEach(cards::add);
		payload.setCards(cards);
		
		return payload;
	}
	
	public static CardPayload cardPayload(String cardNumber, CardType cardType) {
		final var card = new CardPayload();
		card.setNumber(cardNumber);
		card.setType(cardType);
		return card;
	}
	
}
