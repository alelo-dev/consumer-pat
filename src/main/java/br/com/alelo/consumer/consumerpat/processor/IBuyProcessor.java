package br.com.alelo.consumer.consumerpat.processor;

import java.math.BigDecimal;

public interface IBuyProcessor {
	
	BigDecimal calculate(BigDecimal value);
}
