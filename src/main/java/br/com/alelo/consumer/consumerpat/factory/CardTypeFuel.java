package br.com.alelo.consumer.consumerpat.factory;

import java.math.BigDecimal;

public class CardTypeFuel extends AbstractCardBalance {

	@Override
	public BigDecimal calculateFees(BigDecimal value) {

		// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
		final BigDecimal tax  = value.divide(new BigDecimal(100)).multiply(new BigDecimal(35));
		return value.add(tax);
	}

}