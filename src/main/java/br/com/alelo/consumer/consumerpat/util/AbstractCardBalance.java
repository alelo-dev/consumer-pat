package br.com.alelo.consumer.consumerpat.util;

import br.com.alelo.consumer.consumerpat.exception.InsufficientBalanceException;
import br.com.alelo.consumer.consumerpat.exception.ValueInvalidException;

public abstract class AbstractCardBalance {

	public abstract Double calculateFees(Double value);

	public Double calculateCardBalanceForCardType(final Double cardBalance, final Double value) {
		if(cardBalance < 0) {
			throw new InsufficientBalanceException();
		}

		if(value <= 0) {
			throw new ValueInvalidException();
		}

		if(value > cardBalance) {
			throw new InsufficientBalanceException();
		}

		final Double cardBalanceCalculated = cardBalance - this.calculateFees(value);

		if(cardBalanceCalculated < 0) {
			throw new InsufficientBalanceException();
		}

		return cardBalanceCalculated;
	}

}