package br.com.alelo.consumer.consumerpat.application.card.balance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.alelo.consumer.consumerpat.Mocks;
import br.com.alelo.consumer.consumerpat.application.card.balance.response.CardBalanceResponse;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.NotFoundException;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.CardRepository;

class CardBalanceServiceTest {

	@InjectMocks
	private CardBalanceService service = new CardBalanceService();

	@Mock
	private CardRepository cardRepository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		Card card = Mocks.getCard(CardType.DRUGSTORE, BigDecimal.TEN);
		when(cardRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(card));
	}

	@Test
	void testSetBalance() {
		CardBalanceResponse setBalance = service.setBalance(1L, BigDecimal.TEN);
		assertEquals(BigDecimal.valueOf(20), setBalance.getBalance());
	}

	@Test
	void testSetBalanceCardNotFound() {
		try {
			when(cardRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
			service.setBalance(1L, BigDecimal.TEN);
		} catch (NotFoundException ex) {
			assertEquals("card not found", ex.getMessage());
		}
	}

}
