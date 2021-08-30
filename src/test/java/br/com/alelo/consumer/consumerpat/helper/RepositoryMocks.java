package br.com.alelo.consumer.consumerpat.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import br.com.alelo.consumer.consumerpat.domain.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entity.Contact;
import br.com.alelo.consumer.consumerpat.domain.enums.CardType;

public final class RepositoryMocks {
	
	public static Card card(String cardNumber, CardType cardType) {
		final var card = new Card();
		card.setNumber(cardNumber);
		card.setType(cardType);
		card.setBalance(new BigDecimal("100.00"));
		return card;
	}
	
	public static Consumer consumer(Integer id, Card... cards) {
		final var consumer = new Consumer();
		consumer.setId(id);
		consumer.setName("A Consumer");
		consumer.setDocumentNumber("72542961026");
		consumer.setBirthDate(LocalDate.parse("1980-01-01"));
		
		final var address = new Address();
		address.setStreet("A Street");
		address.setNumber("1056");
		address.setPostalCode("0010578");
		address.setCity("A City");
		address.setCountry("Brazil");
		consumer.setAddress(address);
		address.setConsumer(consumer);
		
		final var contact = new Contact();
		contact.setEmail("a.consumer@email");
		contact.setMobilePhoneNumber("+5511981234567");
		contact.setPhoneNumber("+551125503322");
		contact.setResidencePhoneNumber("+551125513456");
		consumer.setContact(contact);
		contact.setConsumer(consumer);
		
		Stream.of(cards).forEach(card -> {
			card.setConsumer(consumer);
			consumer.getCards().add(card);
		});
		
		return consumer;
	}

}
