package br.com.alelo.consumer.consumerpat.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;
import lombok.Data;

@Entity
@Data
public class Card {

	public static final BusinessException CREDIT_IS_ZERO = new BusinessException(
			"the amount to be credited must be greater than zero");
	public static final BusinessException DEBIT_IS_ZERO = new BusinessException(
			"the amount to be debited must be greater than zero");
	public static final BusinessException NOT_AUTHORIZED = new BusinessException(
			"the amount to be debited must be greater than zero");
	public static final BusinessException UNSUPORTED_OPERATION = new BusinessException(
			"unsupported establishment type");

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long number;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "consumer_id", referencedColumnName = "id")
	private Consumer consumer;
	private BigDecimal balance;
	private CardType type;

	public void addFounds(BigDecimal value) {

		if (isZero(value)) {
			throw CREDIT_IS_ZERO;
		}

		this.balance = this.balance.add(value);
	}

	public BigDecimal debit(BigDecimal value) {
		validatedDebit(value);
		BigDecimal debitValue = amountDebitedByType(value);
		this.balance = this.balance.subtract(debitValue);
		return debitValue;
	}

	private void validatedDebit(BigDecimal value) {
		if (isZero(value)) {
			throw DEBIT_IS_ZERO;
		}

		if ((this.balance.subtract(value)).compareTo(BigDecimal.ZERO) < 0) {
			throw NOT_AUTHORIZED;
		}
	}

	private BigDecimal amountDebitedByType(BigDecimal value) {

		switch (type) {
		case DRUGSTORE:
			return value;
		case FOOD:
			return value.multiply(BigDecimal.valueOf(0.9));

		case FUEL:
			return value.multiply(BigDecimal.valueOf(1.35));
		default:
			throw UNSUPORTED_OPERATION;
		}
	}

	private boolean isZero(BigDecimal value) {
		return value.compareTo(BigDecimal.ZERO) <= 0;
	}
}
