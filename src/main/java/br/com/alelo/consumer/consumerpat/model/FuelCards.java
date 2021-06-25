package br.com.alelo.consumer.consumerpat.model;

import java.math.BigInteger;

import br.com.alelo.consumer.consumerpat.model.entity.Cards;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.enums.CardsType;

public class FuelCards extends Cards{
	private static final long serialVersionUID = 1L;
	
	

	public FuelCards(Integer id,Consumer consumer, BigInteger cardNumber, Double cardBalance, CardsType cardsType) {
		super(id, consumer,cardNumber, cardBalance, cardsType);
		
	}
	
	public void tax(Double value) {
		Double tax  = (35.0 /100.0) * value;
		value += tax;
		cardBalance += value;
	}

}
