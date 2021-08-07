package br.com.alelo.consumer.consumerpat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.alelo.consumer.consumerpat.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.NotEnoughBalanceException;
import br.com.alelo.consumer.consumerpat.mock.entity.CardMock;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CardServiceTest {
	
	@Autowired
	private ICardService service;

	@MockBean
	private CardRepository cardRepository;
	
	@Test
	public void whenFindByNumber_thenReturnSuccess() {
		String cardNumber = "1111222233334444";
		when(cardRepository.findByNumber(cardNumber)).thenReturn(Optional.of(CardMock.createFood()));
		
		Card card = service.findByNumber(cardNumber);
		
		assertEquals(card.getType().name(), CardTypeEnum.FOOD.name());
		assertEquals(card.getNumber(), cardNumber);
		assertEquals(card.getBalance(), BigDecimal.valueOf(300));
	}
	
	
	@Test
	public void whenFindByNumber_thenReturnFailed() {
		String cardNumber = "1111222233334444";
		when(cardRepository.findByNumber(cardNumber)).thenReturn(Optional.empty());
		
		try {
			service.findByNumber(cardNumber);
            fail("Expected CardNotFoundException thrown");
        } catch (CardNotFoundException e) {	
    		verify(cardRepository, times(1)).findByNumber(cardNumber);
            assertThat(e).hasMessage("O Cartão informado não foi encontrado! cardNumber: " + cardNumber);
        }
	}
	
	
	@Test
	public void whenSetCardBalance_thenReturnSuccess() {
		String cardNumber = "1111222233334444";
		Double value = 1.1;
		
		CardBalanceDTO cardBalanceDTO = CardBalanceDTO.builder().cardNumber(cardNumber).value(BigDecimal.valueOf(value)).build();
		when(cardRepository.findByNumber(cardNumber)).thenReturn(Optional.of(CardMock.createFood()));
		
		Card card = service.setCardBalance(cardBalanceDTO);
		
		assertEquals(card.getType().name(), CardTypeEnum.FOOD.name());
		assertEquals(card.getNumber(), cardNumber);
		assertEquals(card.getBalance(), BigDecimal.valueOf(301.1));
	}
	
	@Test
	public void whenBuyCardBalance_thenReturnSuccess() {
		String cardNumber = "1111222233334444";
		BigDecimal value = BigDecimal.valueOf(9);
		when(cardRepository.findByNumber(cardNumber)).thenReturn(Optional.of(CardMock.createFood()));
		
		Card card = service.buyCardBalance(cardNumber, value);
		
		assertEquals(card.getType().name(), CardTypeEnum.FOOD.name());
		assertEquals(card.getNumber(), cardNumber);
		assertEquals(card.getBalance(), BigDecimal.valueOf(291));
	}
	
	@Test
	public void whenBuyCardBalance_thenReturnFailed() {
		String cardNumber = "1111222233334444";
		BigDecimal value = BigDecimal.valueOf(301);
		Card card = CardMock.createFood();
		when(cardRepository.findByNumber(cardNumber)).thenReturn(Optional.of(card));
		
		try {
			service.buyCardBalance(cardNumber, value);
			fail("Espera-se um NotEnoughBalanceException, pois o valor é está acima do saldo.");
		} catch (NotEnoughBalanceException e) {
			verify(cardRepository, times(1)).findByNumber(cardNumber);
            assertThat(e).hasMessage("Saldo insuficiente para completar a compra! Saldo disponível: R$ " + card.getBalance());
		}
	}
}
