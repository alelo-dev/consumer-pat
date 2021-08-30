package br.com.alelo.consumer.consumerpat.service;

import static br.com.alelo.consumer.consumerpat.helper.PayloadMocks.buyPayload;
import static br.com.alelo.consumer.consumerpat.helper.RepositoryMocks.card;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeMismatchException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class CardServiceTests {
	
	@Mock
	private CardRepository cardRepository;
	
	@Mock
	private ExtractRepository extractRepository;
	
	@InjectMocks
	private CardService cardService;

	@Captor
	private ArgumentCaptor<Card> cardCaptor;
	
	@Captor
	private ArgumentCaptor<Extract> extractCaptor;
	
	private static final String CARD_NUMBER = "1234567890123456";
	
	@BeforeEach
	@SuppressWarnings("unchecked")
	void beforeEach() {
		reset(cardRepository, extractRepository);
	}
	
	@Test
	void shouldAddValueToBalanceToCard() {
		final var card = card(CARD_NUMBER, CardType.FOOD);
		final var initialBalance = card.getBalance().setScale(2);
		
		when(cardRepository.findById(CARD_NUMBER)).thenReturn(Optional.of(card));
		
		cardService.setBalance(CARD_NUMBER, BigDecimal.TEN);
		
		verify(cardRepository).findById(CARD_NUMBER);
		verify(cardRepository).save(cardCaptor.capture());
		
		final var capturedCard = cardCaptor.getValue();
		assertThat(capturedCard).isEqualTo(card);
		assertThat(capturedCard.getBalance()).isEqualTo(initialBalance.add(BigDecimal.TEN));
	}
	
	@Test
	void shouldThrowExceptionWhenCardIsNotFound() {		
		when(cardRepository.findById(CARD_NUMBER)).thenReturn(Optional.empty());
		
		final var exception = assertThrows(CardNotFoundException.class, 
				() -> cardService.setBalance(CARD_NUMBER, BigDecimal.TEN));
	
		assertTrue(exception.getMessage().contains(CARD_NUMBER));
	}
	
	@Test
	void shouldDoBuyUsingCard() {
		final var card = card(CARD_NUMBER, CardType.FOOD);
		final var payload = buyPayload(CARD_NUMBER, card.getType());
		
		final var initialBalance = card.getBalance().setScale(2);
		final var adjustedValue = card.getType().adjustValue(payload.getValue());
		final var expectedBalance = initialBalance.subtract(adjustedValue);
		
		when(cardRepository.findById(CARD_NUMBER)).thenReturn(Optional.of(card));
		
		cardService.buy(CARD_NUMBER, payload);
		
		verify(cardRepository).findById(CARD_NUMBER);
		verify(cardRepository).save(cardCaptor.capture());
		verify(extractRepository).save(extractCaptor.capture());
		
		final var capturedCard = cardCaptor.getValue();
		assertThat(capturedCard).isEqualTo(card);		
		assertThat(capturedCard.getBalance()).isEqualTo(expectedBalance);
		
		final var capturedExtract = extractCaptor.getValue();
		assertThat(capturedExtract.getCardNumber()).isEqualTo(CARD_NUMBER);
		assertThat(capturedExtract.getEstablishmentName()).isEqualTo(payload.getEstablishmentName());
		assertThat(capturedExtract.getProductDescription()).isEqualTo(payload.getProductDescription());
		assertThat(capturedExtract.getValue()).isEqualTo(adjustedValue);
	}
	
	@Test
	void shouldCancelBuyWhenCardIsNotFound() {
		final var payload = buyPayload(CARD_NUMBER,CardType.FOOD);
		
		when(cardRepository.findById(CARD_NUMBER)).thenReturn(Optional.empty());
		
		final var exception = assertThrows(CardNotFoundException.class, 
				() -> cardService.buy(CARD_NUMBER, payload));
	
		assertTrue(exception.getMessage().contains(CARD_NUMBER));
		
		verify(cardRepository).findById(CARD_NUMBER);
		verify(cardRepository, never()).save(any());
		verify(extractRepository, never()).save(any());
	}
	
	@Test
	void shouldCancelBuyWhenCardTypeAndEstablishmentTypeMismatches() {
		final var card = card(CARD_NUMBER, CardType.FOOD);
		final var payload = buyPayload(CARD_NUMBER, CardType.DRUGSTORE);
		
		when(cardRepository.findById(CARD_NUMBER)).thenReturn(Optional.of(card));
	
		final var exception = assertThrows(CardTypeMismatchException.class, 
				() -> cardService.buy(CARD_NUMBER, payload));
		
		assertTrue(exception.getMessage().contains(card.getType().toString()));
		assertTrue(exception.getMessage().contains(payload.getEstablishmentType().toString()));
	
		verify(cardRepository).findById(CARD_NUMBER);
		verify(cardRepository, never()).save(any());
		verify(extractRepository, never()).save(any());
	}
	
}
