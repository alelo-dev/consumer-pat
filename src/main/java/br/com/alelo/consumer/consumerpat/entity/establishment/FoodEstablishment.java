package br.com.alelo.consumer.consumerpat.entity.establishment;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public class FoodEstablishment extends Establishment {

	@Override
	public double discountCalculator(double value) {
		Double cashback  = (value / 100) * 10;
        value = value - cashback;
		return value;
	}

	@Override
	public Consumer setCard(int cardNumber, double value) {
		Consumer consumer = repository.findByFoodCardNumber(cardNumber);
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
		return consumer;
	}
}
