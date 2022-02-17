package br.com.alelo.consumer.consumerpat.service;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.card.Card;

@Service
public class CardService {
	
	public Consumer executa(Card cartao, int cardNumber, double valueCard) {
		return cartao.execute(cardNumber, valueCard);
	}

	
	
	

}
