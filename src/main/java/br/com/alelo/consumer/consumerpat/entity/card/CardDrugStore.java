package br.com.alelo.consumer.consumerpat.entity.card;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.Data;

@Data
public class CardDrugStore extends Card {
	
	private Consumer consumer = null;
	
	public CardDrugStore(Card next) {
		super(next);
	}

	@Override
	public void setCard(int cardNumber, double valueCard) {
		consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + valueCard);
	}

	@Override
	public Consumer findConsumer(int cardNumber) {
		consumer = repository.findByDrugstoreNumber(cardNumber);
		return consumer;
	}

}
