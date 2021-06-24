package br.com.alelo.consumer.consumerpat.factory;

import java.math.BigDecimal;

public class CardTypeFood extends AbstractCardBalance {

	@Override
	public BigDecimal calculateFees(BigDecimal value) {
		// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
		final BigDecimal cashback  = value.divide(new BigDecimal(100)).multiply(new BigDecimal(10));
		return value.subtract(cashback);
	}

}