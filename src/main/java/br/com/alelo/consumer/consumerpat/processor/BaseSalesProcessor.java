package br.com.alelo.consumer.consumerpat.processor;

import br.com.alelo.consumer.consumerpat.exception.NotEnoughBalanceException;

public abstract class BaseSalesProcessor implements SellStrategy {

	public void validateBalance(double valueWithTax, double currentBalance) {

		if (valueWithTax > currentBalance) {
			throw new NotEnoughBalanceException(currentBalance);
		}
	}

}
