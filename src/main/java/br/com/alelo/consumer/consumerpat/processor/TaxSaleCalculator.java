package br.com.alelo.consumer.consumerpat.processor;

import org.springframework.stereotype.Component;

@Component
public class TaxSaleCalculator {

	public double calcular(SellStrategy sellStrategy) { 
		return sellStrategy.calculate(); 
	} 

}
