package br.com.alelo.consumer.consumerpat.domain.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityNotFoundException;

@Service
public class CardServiceUtils {

	public String generateCardNumber() {
		final long MIN = 1000000000000000L;
		final long MAX = 9999999999999999L;
		Long actual = Long.valueOf(Math.abs(Float.valueOf(new Random().nextFloat() * (MAX - MIN)).longValue()));
		String number = actual.toString();
		return number;
	}
	
	public Card validateConsumerCard(Consumer consumer, String cardNumber) {
		Card cardManaged = null; 
		
		for(Card card : consumer.getCards()) {
			if(card.getNumber().contains(cardNumber)){
				cardManaged = card;
				break;
			}
		}
		
		if(cardManaged == null) {
			throw new EntityNotFoundException(
					String.format("Consumidor não possui o cartão de número %s!", cardNumber));
		}
		
		return cardManaged;
	}
}
