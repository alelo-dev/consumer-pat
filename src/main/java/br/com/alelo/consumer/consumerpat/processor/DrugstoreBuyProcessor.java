package br.com.alelo.consumer.consumerpat.processor;

import java.math.BigDecimal;

public class DrugstoreBuyProcessor implements IBuyProcessor {

	@Override
	public BigDecimal calculate(BigDecimal value) {
		return value;
	}

}
