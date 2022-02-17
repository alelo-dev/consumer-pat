package br.com.alelo.consumer.consumerpat.entity.card;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.Data;

@Data
public class CardFood extends Card {
	
	private Consumer consumer = null;

	public CardFood(Card next) {
		super(next);
	}

	@Override
	public void setCard(int cardNumber, double valueCard) {
		consumer.setFoodCardBalance(consumer.getFoodCardBalance() + valueCard);
	}

	@Override
	public Consumer findConsumer(int cardNumber) {
		consumer = repository.findByFoodCardNumber(cardNumber);
		return consumer;
	}

}
