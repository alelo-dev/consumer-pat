package br.com.alelo.consumer.consumerpat.processor;

import org.springframework.stereotype.Component;

@Component
public interface SellStrategy {

	double calculate();
}
