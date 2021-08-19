package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.model.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.model.dto.SetBalanceDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Card;

public interface CardService {
	
	List<Card> listAllCards();
	
	Card createCard(Card card);
	
	Card updateCard(Card card);
	
	Card setBalance(SetBalanceDTO setBalanceDTO);
	
	void buy(BuyDTO buyDTO);

}
