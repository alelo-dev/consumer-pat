package br.com.alelo.consumer.consumerpat.util;

public class CardTypeFuel extends AbstractCardBalance {

	@Override
	public Double calculateFees(Double value) {

		// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
		final Double tax  = (value / 100) * 35;
		value = value + tax;

		return value;
	}

}