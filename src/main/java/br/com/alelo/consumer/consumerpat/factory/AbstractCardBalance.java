package br.com.alelo.consumer.consumerpat.factory;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.exception.InsufficientBalanceException;
import br.com.alelo.consumer.consumerpat.exception.ValueInvalidException;

public abstract class AbstractCardBalance {

	public abstract BigDecimal calculateFees(BigDecimal value);

	public BigDecimal calculateCardBalanceForCardType(final BigDecimal cardBalance, final BigDecimal value) {
		if(cardBalance.compareTo(BigDecimal.ZERO) < 0) {
			throw new InsufficientBalanceException();
		}

		if(value.compareTo(BigDecimal.ZERO) < 1) {
			throw new ValueInvalidException();
		}

		if(value.compareTo(cardBalance) > 0) {
			throw new InsufficientBalanceException();
		}

		final BigDecimal cardBalanceCalculated = cardBalance.subtract(this.calculateFees(value));

		if(cardBalanceCalculated.compareTo(BigDecimal.ZERO) < 0) {
			throw new InsufficientBalanceException();
		}

		return cardBalanceCalculated;
	}

}