package br.com.alelo.consumer.consumerpat.model;

import java.math.BigInteger;

import br.com.alelo.consumer.consumerpat.model.entity.Cards;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.enums.CardsType;

public class EstablishementCards extends Cards{
	private static final long serialVersionUID = 1L;
	
	
	
	public EstablishementCards(Integer id, Consumer consumer, BigInteger cardNumber, Double cardBalance,
			CardsType cardsType) {
		super(id, consumer, cardNumber, cardBalance, cardsType);
		// TODO Auto-generated constructor stub
	}

	public EstablishementCards() {
		// TODO Auto-generated constructor stub
	}

	public void cashback(Double value) {
		Double cashback  = (10.0 / 100.0) * value;
		value = value - cashback;
		cardBalance = cardBalance -value;
		System.out.println(cardBalance);
	}

}
