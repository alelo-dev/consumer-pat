package br.com.alelo.consumer.consumerpat.service;

import static br.com.alelo.consumer.consumerpat.domain.enums.CardType.FOOD;
import static br.com.alelo.consumer.consumerpat.domain.enums.CardType.FUEL;
import static br.com.alelo.consumer.consumerpat.helper.PayloadMocks.cardPayload;
import static br.com.alelo.consumer.consumerpat.helper.PayloadMocks.consumerPayload;
import static br.com.alelo.consumer.consumerpat.helper.RepositoryMocks.card;
import static br.com.alelo.consumer.consumerpat.helper.RepositoryMocks.consumer;
import static br.com.alelo.consumer.consumerpat.helper.UtilMocks.page;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.payload.CardPayload;
import br.com.alelo.consumer.consumerpat.exception.CardAlreadyExistsException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class ConsumerServiceTests {
	
	@Mock
	private ConsumerRepository consumerRepository;
	
	@Mock
	private CardRepository cardRepository;

	@InjectMocks
	private ConsumerService consumerService;

	@Captor
	private ArgumentCaptor<Consumer> consumerCaptor;
	
	private static final String CARD_NUMBER = "1234123412341234";
	
	private static final String CARD_NUMBER_2 = "3555355535553555";
	
	private static final int ID = 1;
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	void beforeEach() {
		reset(consumerRepository, cardRepository);
	}
	
	@Test
	void shouldListAllConsumers() {
		final var pageRequest = PageRequest.of(0, 50);
		final var consumersPage = page(pageRequest, 
						consumer(1, card(CARD_NUMBER, FOOD)),
						consumer(2, card("2345234523452345", FOOD)));
		
		when(consumerRepository.findAll(pageRequest)).thenReturn(consumersPage);
	
		final var result = consumerService.listAllConsumers(pageRequest);
		
		assertNotNull(result);
		assertThat(result.getContent()).hasSize(2);
	}
	
	@Test
	void shouldCreateConsumer() {
		final var payload = consumerPayload(cardPayload(CARD_NUMBER, FOOD));
	
		when(cardRepository.existsById(CARD_NUMBER)).thenReturn(false);
		
		consumerService.createConsumer(payload);
		
		verify(cardRepository).existsById(CARD_NUMBER);
		verify(consumerRepository).save(consumerCaptor.capture());
		
		final var consumer = consumerCaptor.getValue();
		assertThat(payload.getName()).isEqualTo(consumer.getName());
		assertThat(payload.getDocumentNumber()).isEqualTo(consumer.getDocumentNumber());
		assertThat(payload.getBirthDate()).isEqualTo(consumer.getBirthDate());
		
		final var addressDTO = payload.getAddress();
		final var address = consumer.getAddress();
		assertThat(addressDTO.getStreet()).isEqualTo(address.getStreet());
		assertThat(addressDTO.getNumber()).isEqualTo(address.getNumber());
		assertThat(addressDTO.getPostalCode()).isEqualTo(address.getPostalCode());
		assertThat(addressDTO.getCity()).isEqualTo(address.getCity());
		assertThat(addressDTO.getCountry()).isEqualTo(address.getCountry());
		
		final var contactDTO = payload.getContact();
		final var contact = consumer.getContact();
		assertThat(contactDTO.getMobilePhoneNumber()).isEqualTo(contact.getMobilePhoneNumber());
		assertThat(contactDTO.getPhoneNumber()).isEqualTo(contact.getPhoneNumber());
		assertThat(contactDTO.getResidencePhoneNumber()).isEqualTo(contact.getResidencePhoneNumber());
		assertThat(contactDTO.getEmail()).isEqualTo(contact.getEmail());
		
		assertThat(payload.getCards().size()).isEqualTo(consumer.getCards().size());
		
		final var cardDTO = payload.getCards().stream().findFirst().get();
		final var card = consumer.getCards().stream().findFirst().get();
		assertThat(cardDTO.getNumber()).isEqualTo(card.getNumber());
		assertThat(cardDTO.getType()).isEqualTo(card.getType());
		assertThat(card.getBalance()).isZero();
	}
	
	@Test
	void shouldFailToCreateConsumerWhenCardAlreadyExists() {
		final var payload = consumerPayload(cardPayload(CARD_NUMBER, FOOD));
	
		when(cardRepository.existsById(CARD_NUMBER)).thenReturn(true);
		
		final var exception = assertThrows(CardAlreadyExistsException.class, () -> consumerService.createConsumer(payload));
	
		assertTrue(exception.getMessage().contains(CARD_NUMBER));
		
		verify(cardRepository).existsById(CARD_NUMBER);
		verify(consumerRepository, never()).save(any());
	}
	
	@Test
	void shouldUpdateConsumer() {
		final var consumer = consumer(ID, card(CARD_NUMBER, FOOD), card("2345234523452345", FUEL));
		
		final var payload = consumerPayload(
				cardPayload(CARD_NUMBER, FOOD), cardPayload(CARD_NUMBER_2, FUEL));
	
		when(consumerRepository.findById(ID)).thenReturn(Optional.of(consumer));
		
		consumerService.updateConsumer(ID, payload);
		
		verify(consumerRepository).findById(ID);
		verify(cardRepository, never()).existsById(CARD_NUMBER);
		verify(cardRepository).existsById(CARD_NUMBER_2);
		verify(consumerRepository).save(consumerCaptor.capture());
		
		final var capturedConsumer = consumerCaptor.getValue();
		assertThat(payload.getName()).isEqualTo(capturedConsumer.getName());
		assertThat(payload.getDocumentNumber()).isEqualTo(capturedConsumer.getDocumentNumber());
		assertThat(payload.getBirthDate()).isEqualTo(capturedConsumer.getBirthDate());
		
		final var addressDTO = payload.getAddress();
		final var address = capturedConsumer.getAddress();
		assertThat(addressDTO.getStreet()).isEqualTo(address.getStreet());
		assertThat(addressDTO.getNumber()).isEqualTo(address.getNumber());
		assertThat(addressDTO.getPostalCode()).isEqualTo(address.getPostalCode());
		assertThat(addressDTO.getCity()).isEqualTo(address.getCity());
		assertThat(addressDTO.getCountry()).isEqualTo(address.getCountry());
		
		final var contactDTO = payload.getContact();
		final var contact = capturedConsumer.getContact();
		assertThat(contactDTO.getMobilePhoneNumber()).isEqualTo(contact.getMobilePhoneNumber());
		assertThat(contactDTO.getPhoneNumber()).isEqualTo(contact.getPhoneNumber());
		assertThat(contactDTO.getResidencePhoneNumber()).isEqualTo(contact.getResidencePhoneNumber());
		assertThat(contactDTO.getEmail()).isEqualTo(contact.getEmail());
		
		assertThat(payload.getCards().size()).isEqualTo(capturedConsumer.getCards().size());
		
		final var cardsDTO = payload.getCards()
				.stream()
				.collect(Collectors.toMap(CardPayload::getNumber, Function.identity()));
		
		capturedConsumer
			.getCards()
			.stream()
			.forEach(card -> {
				final var cardDTO = cardsDTO.get(card.getNumber());
				assertThat(cardDTO.getNumber()).isEqualTo(card.getNumber());
				assertThat(cardDTO.getType()).isEqualTo(card.getType());
			});
	}
	
	@Test
	void shouldFailToUpdateConsumerWhenItIsNotFound() {
		final var payload = consumerPayload(
				cardPayload(CARD_NUMBER, FOOD), cardPayload(CARD_NUMBER_2, FUEL));
	
		when(consumerRepository.findById(ID)).thenReturn(Optional.empty());
		
		final var exception = assertThrows(ConsumerNotFoundException.class, () -> consumerService.updateConsumer(ID, payload));
	
		assertTrue(exception.getMessage().contains(String.valueOf(ID)));

		verify(consumerRepository).findById(ID);
		verify(cardRepository, never()).existsById(CARD_NUMBER);
		verify(cardRepository, never()).existsById(CARD_NUMBER_2);
		verify(consumerRepository, never()).save(any());
	}
	
	@Test
	void shouldFailToUpdateConsumerWhenNewCardIsAlreadyInUse() {
		final var consumer = consumer(ID, card(CARD_NUMBER, FOOD), card("2345234523452345", FUEL));
		
		final var payload = consumerPayload(
				cardPayload(CARD_NUMBER, FOOD), cardPayload(CARD_NUMBER_2, FUEL));
	
		when(consumerRepository.findById(ID)).thenReturn(Optional.of(consumer));
		when(cardRepository.existsById(CARD_NUMBER_2)).thenReturn(true);
		
		final var exception = assertThrows(CardAlreadyExistsException.class, () -> consumerService.updateConsumer(ID, payload));
		
		assertTrue(exception.getMessage().contains(CARD_NUMBER_2));
		
		verify(consumerRepository).findById(ID);
		verify(cardRepository, never()).existsById(CARD_NUMBER);
		verify(cardRepository).existsById(CARD_NUMBER_2);
		verify(consumerRepository, never()).save(any());
	}
	
}
