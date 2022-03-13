package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.CardValueTransactionDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.model.dto.RequestBuyDTO;


public interface CardService {
	
	CardDTO setBalance(CardValueTransactionDTO cardDTO);
	
	Card findByCardNumber(Integer cardNumber);
	
	ExtractDTO buyProductWithCard(RequestBuyDTO requestBuyDTO);
}
