package br.com.alelo.consumer.consumerpat.application.card.buy;

import static br.com.alelo.consumer.consumerpat.Mocks.getCard;
import static br.com.alelo.consumer.consumerpat.Mocks.getExtract;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.alelo.consumer.consumerpat.application.card.buy.request.CardBuyRequest;
import br.com.alelo.consumer.consumerpat.application.card.buy.response.TransactionResponse;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.CardType;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.ErrorMessageDto;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.ExtractRepository;

class CardBuyServiceTest {

	@InjectMocks
	private CardBuyService service = new CardBuyService();

	@Mock
	private CardRepository repository;

	@Mock
	private ExtractRepository extractRepository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		Card card = getCard(CardType.DRUGSTORE, BigDecimal.TEN);
		when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(card));
		when(extractRepository.save(ArgumentMatchers.any())).thenReturn(getExtract());
	}

	@Test
	void testBuy() {

		TransactionResponse buy = service.buy(getRequest(BigDecimal.ONE));
		assertNotNull(buy);
		assertEquals(1, buy.getId());
		assertNotNull(buy.getDateBuy());
		assertNotNull(buy.getCardNumber());
		assertNotNull(buy.getEstablishmentName());
		assertNotNull(buy.getProductDescription());
		assertEquals(BigDecimal.ONE, buy.getValue());
	}

	@Test
	void testBuyRequestInvalid() {

		try {
			CardBuyRequest request = new CardBuyRequest();
			service.buy(request);
		} catch (BusinessException ex) {
			assertEquals("invalid buy request", ex.getMessage());
			ErrorMessageDto buildResponse = ex.buildResponse();
			assertEquals(3, buildResponse.getErrors().size());
		}

	}

	private CardBuyRequest getRequest(BigDecimal value) {
		CardBuyRequest request = new CardBuyRequest();
		request.setCardNumber(1L);
		request.setEstablishmentName("test");
		request.setProductDescription("test product");
		request.setValue(value);
		return request;
	}
}
