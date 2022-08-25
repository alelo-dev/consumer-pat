package br.com.alelo.consumer.consumerpat.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.emun.TypeCard;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Establishment;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.LancamentoRepository;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

	@Mock
	private CardRepository cardRepository;

	@Mock
	private EstablishmentService establishmentService;

	@Mock
	private LancamentoRepository lancamentoRepository;
	
	@InjectMocks
	private CardService cardService;
	
	
	@Test
	void testAssign() {
		Card card = new Card();
		card.setBalance(0);
		card.setType(TypeCard.DRUG);
		card.setNumber(1L);
		
		when(cardRepository.findByCardNumber(1L)).thenReturn(card);
				
		cardService.assign(1L, 100);
		
		verify(cardRepository, times(1)).findByCardNumber(1L);
		verify(cardRepository, times(1)).save(card);
	}

	@Test
	void testBuy() {
		Card card = new Card();
		card.setBalance(0);
		card.setType(TypeCard.DRUG);
		card.setNumber(1L);
		
		when(cardRepository.findByCardNumber(1L)).thenReturn(card);
		
		Establishment establishment = new Establishment();
		establishment.setId(1L);
		establishment.setName("Name");
		establishment.setType(TypeCard.DRUG);
		
		when(establishmentService.findById(1L)).thenReturn(establishment);
		
		BuyDto buy = new BuyDto();
		buy.setEstablishmentId(1L);
		buy.setProductDescription("Desc");
		buy.setValue(20);
		
		cardService.buy(1L, buy);;
		
		verify(cardRepository, times(1)).save(card);
		verify(lancamentoRepository, times(1)).save(any());

	}

}
