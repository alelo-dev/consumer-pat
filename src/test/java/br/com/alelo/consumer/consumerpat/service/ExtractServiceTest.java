package br.com.alelo.consumer.consumerpat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
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

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.CardNotAcceptedException;
import br.com.alelo.consumer.consumerpat.exception.NotEnoughBalanceException;
import br.com.alelo.consumer.consumerpat.mock.dto.ExtractDTOMock;
import br.com.alelo.consumer.consumerpat.mock.entity.CardMock;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ExtractServiceTest {
	
	@Autowired
	private IExtractService service;
	
	@MockBean
	private ExtractRepository extractRepository;
	
	@MockBean
	private CardRepository cardRepository;
	
	@Test
	public void whenBuyFood_thenReturnSuccess() {
		Double value = 20.0;
		ExtractDTO extractDTO = ExtractDTOMock.createFood(value);
		when(cardRepository.findByNumber(extractDTO.getCardNumber())).thenReturn(Optional.of(CardMock.createFood()));
		
		service.buy(extractDTO);
		
		verify(cardRepository, times(2)).findByNumber(extractDTO.getCardNumber());
		verify(extractRepository, times(1)).save(any());
	}
	
	@Test
	public void whenBuyFoodBalanceValueExactly_thenReturnSuccess() {
		Double value = 20.0;
		ExtractDTO extractDTO = ExtractDTOMock.createFood(value);
		Card card = CardMock.createFood();
		card.setBalance(BigDecimal.valueOf(18));
		when(cardRepository.findByNumber(extractDTO.getCardNumber())).thenReturn(Optional.of(card));
		
		service.buy(extractDTO);
		
		verify(cardRepository, times(2)).findByNumber(extractDTO.getCardNumber());
		verify(extractRepository, times(1)).save(any());
	}
	
	@Test
	public void whenBuyDrugstoreBalanceValueExactly_thenReturnSuccess() {
		Double value = 10.0;
		ExtractDTO extractDTO = ExtractDTOMock.createDrugstore(value);
		Card card = CardMock.createDrugstore();
		card.setBalance(BigDecimal.valueOf(10));
		when(cardRepository.findByNumber(extractDTO.getCardNumber())).thenReturn(Optional.of(card));
		
		service.buy(extractDTO);
		
		verify(cardRepository, times(2)).findByNumber(extractDTO.getCardNumber());
		verify(extractRepository, times(1)).save(any());
	}
	
	@Test
	public void whenBuyFuelBalanceValueExactly_thenReturnSuccess() {
		Double value = 20.0;
		ExtractDTO extractDTO = ExtractDTOMock.createFuel(value);
		Card card = CardMock.createFuel();
		card.setBalance(BigDecimal.valueOf(27));
		when(cardRepository.findByNumber(extractDTO.getCardNumber())).thenReturn(Optional.of(card));
		
		service.buy(extractDTO);
		
		verify(cardRepository, times(2)).findByNumber(extractDTO.getCardNumber());
		verify(extractRepository, times(1)).save(any());
	}


	@Test
	public void whenBuyFoodNotBalanceValue_thenReturnFailed() {
		Double value = 20.0;
		ExtractDTO extractDTO = ExtractDTOMock.createFood(value);
		Card card = CardMock.createFood();
		card.setBalance(BigDecimal.valueOf(10));
		when(cardRepository.findByNumber(extractDTO.getCardNumber())).thenReturn(Optional.of(card));
		
		try {
			service.buy(extractDTO);
			fail("Espera-se um NotEnoughBalanceException, pois o valor é está acima do saldo.");
		} catch (NotEnoughBalanceException e) {
			verify(cardRepository, times(2)).findByNumber(extractDTO.getCardNumber());
            assertThat(e).hasMessage("Saldo insuficiente para completar a compra! Saldo disponível: R$ " + card.getBalance());
		}
	}
	
	@Test
	public void whenBuyDrugstoreNotBalanceValue_thenReturnFailed() {
		Double value = 10.1;
		ExtractDTO extractDTO = ExtractDTOMock.createDrugstore(value);
		Card card = CardMock.createDrugstore();
		card.setBalance(BigDecimal.valueOf(10));
		when(cardRepository.findByNumber(extractDTO.getCardNumber())).thenReturn(Optional.of(card));
		
		try {
			service.buy(extractDTO);
			fail("Espera-se um NotEnoughBalanceException, pois o valor é está acima do saldo.");
		} catch (NotEnoughBalanceException e) {
			verify(cardRepository, times(2)).findByNumber(extractDTO.getCardNumber());
            assertThat(e).hasMessage("Saldo insuficiente para completar a compra! Saldo disponível: R$ " + card.getBalance());
		}
	}
	
	@Test
	public void whenBuyFuelNotBalanceValue_thenReturnFailed() {
		Double value = 20.0;
		ExtractDTO extractDTO = ExtractDTOMock.createFuel(value);
		Card card = CardMock.createFuel();
		card.setBalance(BigDecimal.valueOf(26.99));
		when(cardRepository.findByNumber(extractDTO.getCardNumber())).thenReturn(Optional.of(card));
		
		try {
			service.buy(extractDTO);
			fail("Espera-se um NotEnoughBalanceException, pois o valor é está acima do saldo.");
		} catch (NotEnoughBalanceException e) {
			verify(cardRepository, times(2)).findByNumber(extractDTO.getCardNumber());
            assertThat(e).hasMessage("Saldo insuficiente para completar a compra! Saldo disponível: R$ " + card.getBalance());
		}
	}
	
	
	@Test
	public void whenBuyFoodEstablishmentOnCardFuel_thenReturnFailed() {
		Double value = 20.0;
		ExtractDTO extractDTO = ExtractDTOMock.createFood(value);
		extractDTO.setCardNumber("1111222233334444");
		Card card = CardMock.createFuel();
		card.setNumber(extractDTO.getCardNumber());
		when(cardRepository.findByNumber(extractDTO.getCardNumber())).thenReturn(Optional.of(card));
		
		try {
			service.buy(extractDTO);
            fail("Espera-se um CardNotAcceptedException pois o cartão informado não pode ser utilizado para uma compra no estabelecimento.");
        } catch (CardNotAcceptedException e) {
    		verify(cardRepository, times(1)).findByNumber(extractDTO.getCardNumber());
            assertThat(e).hasMessage("O Cartão informado não pode ser usado para este tipo de estabelecimento! Tipo de Estabelecimento: " + extractDTO.getEstablishmentType() + ", Tipo do cartão: "+ card.getType());
        }
	}
}
