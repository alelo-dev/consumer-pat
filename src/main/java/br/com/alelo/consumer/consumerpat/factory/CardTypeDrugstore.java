package br.com.alelo.consumer.consumerpat.factory;

import java.math.BigDecimal;

public class CardTypeDrugstore extends AbstractCardBalance {

	@Override
	public BigDecimal calculateFees(final BigDecimal value) {
		return BigDecimal.ZERO;
	}

}