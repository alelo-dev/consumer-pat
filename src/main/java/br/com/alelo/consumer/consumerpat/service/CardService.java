package br.com.alelo.consumer.consumerpat.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;

@Service
public class CardService {

	
	
	@Autowired
	private CardRepository cardRepositoy;
	
	public Card findCardByNumber(String cardNumber){
		Optional<Card> optional = cardRepositoy.getCardByNumber(cardNumber);
				
		return optional.orElseThrow(() ->
			new CardNotFoundException());
	}
	
	public Card save(Card card){
		
		Card cardBanco = cardRepositoy.save(card);
				
		return cardBanco;
	}
	
	public Card setBalance(Card card) {
		
		Card cardBanco = cardRepositoy.getCardByNumber(card.getNumber()).get();
		
		cardBanco.setBalance(cardBanco.getBalance() + card.getBalance());
		
		cardRepositoy.save(cardBanco);
		
        
        return cardBanco;
	}	
}
