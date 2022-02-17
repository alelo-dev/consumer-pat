package br.com.alelo.consumer.consumerpat.entity.establishment;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public class FuelEstablishment extends Establishment {

	@Override
	public double discountCalculator(double value) {
		Double tax  = (value / 100) * 35;
        value = value + tax;
        return value;
	}

	@Override
	public Consumer setCard(int cardNumber, double value) {
		 Consumer consumer = repository.findByFuelCardNumber(cardNumber);
         consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
         return consumer;
	}
}
