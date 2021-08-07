package br.com.alelo.consumer.consumerpat.processor;

import java.math.BigDecimal;

public class FuelBuyProcessor implements IBuyProcessor {

	@Override
	public BigDecimal calculate(BigDecimal value) {
		return value.multiply(BigDecimal.valueOf(1.35));
		
	}

}
