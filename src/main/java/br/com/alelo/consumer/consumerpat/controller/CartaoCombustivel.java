package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.card.Card;

public class CartaoCombustivel extends Card {
	
	private Consumer consumer = null;

	public CartaoCombustivel(Card next) {
		super(next);
	}

	public Consumer getConsumer() {
		return consumer;
	}

	@Override
	public void setCard(int cardNumber, double valueCard) {
         consumer.setFuelCardBalance(consumer.getFuelCardBalance() + valueCard);
	}

	@Override
	public Consumer findConsumer(int cardNumber) {
		 consumer = repository.findByFuelCardNumber(cardNumber);
		 return consumer;
	}
}
