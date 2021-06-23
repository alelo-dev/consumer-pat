package br.com.alelo.consumer.consumerpat.util;

public class CardTypeFood extends AbstractCardBalance {

	@Override
	public Double calculateFees(Double value) {

		// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
		final Double cashback  = (value / 100) * 10;
		value = value - cashback;

		return value;
	}

}