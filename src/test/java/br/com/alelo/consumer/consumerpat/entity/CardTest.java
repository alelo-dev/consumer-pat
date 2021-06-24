package br.com.alelo.consumer.consumerpat.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

class CardTest {

	@Test
	void registerTransactionDebit() {
		Card card = new Card();
		card.setBalance(new BigDecimal("100.0"));

		card.registerTransactionDebit(new BigDecimal("20.0"));
		assertEquals(BigDecimal.valueOf(80.0), card.getBalance());

		card.registerTransactionDebit(new BigDecimal("10.5"));
		assertEquals(BigDecimal.valueOf(69.5), card.getBalance());
	}
}