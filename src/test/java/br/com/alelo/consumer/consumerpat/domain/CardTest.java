package br.com.alelo.consumer.consumerpat.domain;

import static br.com.alelo.consumer.consumerpat.Mocks.getCard;
import static br.com.alelo.consumer.consumerpat.domain.Card.CREDIT_IS_ZERO;
import static br.com.alelo.consumer.consumerpat.domain.Card.DEBIT_IS_ZERO;
import static br.com.alelo.consumer.consumerpat.domain.Card.NOT_AUTHORIZED;
import static br.com.alelo.consumer.consumerpat.domain.CardType.DRUGSTORE;
import static br.com.alelo.consumer.consumerpat.domain.CardType.FOOD;
import static br.com.alelo.consumer.consumerpat.domain.CardType.FUEL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;

class CardTest {

	private BigDecimal balance = BigDecimal.TEN;

	@Test
	void testAddFounds() {
		Card card = getCard(FUEL, balance);
		card.addFounds(BigDecimal.ONE);
		assertEquals(BigDecimal.valueOf(11), card.getBalance());
	}

	@Test
	void testAddFoundsError() {
		try {
			Card card = getCard(DRUGSTORE, balance);
			card.addFounds(BigDecimal.ZERO);
		} catch (BusinessException ex) {
			assertEquals(CREDIT_IS_ZERO.getMessage(), ex.getMessage());
		}
	}

	@Test
	void testDebitDrugstore() {
		Card card = getCard(DRUGSTORE, balance);
		card.debit(BigDecimal.ONE);
		assertEquals(BigDecimal.valueOf(9), card.getBalance());
	}

	@Test
	void testDebitFuel() {
		BigDecimal debitValue = BigDecimal.ONE;
		Card card = getCard(FUEL, balance);
		card.debit(BigDecimal.ONE);
		BigDecimal remainingBalance = balance.subtract(debitValue.multiply(BigDecimal.valueOf(1.35)));
		assertEquals(remainingBalance, card.getBalance());
	}

	@Test
	void testDebitFood() {
		BigDecimal debitValue = BigDecimal.ONE;
		Card card = getCard(FOOD, balance);
		card.debit(BigDecimal.ONE);
		BigDecimal remainingBalance = balance.subtract(debitValue.multiply(BigDecimal.valueOf(0.9)));
		assertEquals(remainingBalance, card.getBalance());
	}

	@Test
	void testDebitNotFunds() {
		try {
			Card card = getCard(DRUGSTORE, balance);
			card.debit(BigDecimal.valueOf(11));
		} catch (BusinessException ex) {
			assertEquals(NOT_AUTHORIZED.getMessage(), ex.getMessage());
		}
	}

	@Test
	void testDebitZero() {
		try {
			Card card = getCard(DRUGSTORE, balance);
			card.debit(BigDecimal.ZERO);
		} catch (BusinessException ex) {
			assertEquals(DEBIT_IS_ZERO.getMessage(), ex.getMessage());
		}
	}
}
