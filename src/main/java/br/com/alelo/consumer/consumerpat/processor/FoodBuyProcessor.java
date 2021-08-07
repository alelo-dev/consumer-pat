package br.com.alelo.consumer.consumerpat.processor;

import java.math.BigDecimal;

public class FoodBuyProcessor implements IBuyProcessor {

	@Override
	public BigDecimal calculate(BigDecimal value) {
		return value.multiply(BigDecimal.valueOf(0.9));
	}

}
