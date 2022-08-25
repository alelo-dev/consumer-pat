package br.com.alelo.consumer.consumerpat.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alelo.consumer.consumerpat.emun.TypeCard;

class CardTest {

	@Test
	void testDebitFood() {
		Card card = new Card();
		card.setBalance(100);
		card.setType(TypeCard.FOOD);
		double value = card.debit(10);
		assertEquals(9, value);
		assertEquals(91, card.getBalance());
		
	}

	@Test
	void testDebitDrug() {
		Card card = new Card();
		card.setBalance(100);
		card.setType(TypeCard.DRUG);
		double value = card.debit(10);
		assertEquals(10, value);
		assertEquals(90, card.getBalance());
		
	}
	
	@Test
	void testDebitFuel() {
		Card card = new Card();
		card.setBalance(100);
		card.setType(TypeCard.FUEL);
		double value = card.debit(10);
		assertEquals(13.5, value);
		assertEquals(86.5, card.getBalance());
		
	}

}
