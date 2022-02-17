package br.com.alelo.consumer.consumerpat.entity.card;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Card {
	
	@Autowired
	protected ConsumerRepository repository;
	
	private Card next;
	
	public Card(Card next) {
		this.next = next;
	}
	
	public abstract void setCard(int cardNumber, double valueCard);
	
	public abstract Consumer findConsumer(int cardNumber);
	
	public Consumer execute(int cardNumber, double valueCard) {
		 Consumer consumer = this.findConsumer(cardNumber);
		 if (consumer != null) {
			 setCard(cardNumber, valueCard);
			 return consumer;
		 }
		 return this.next.execute(cardNumber, valueCard);
	}
}
