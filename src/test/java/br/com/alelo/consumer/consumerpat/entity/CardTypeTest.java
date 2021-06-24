package br.com.alelo.consumer.consumerpat.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static br.com.alelo.consumer.consumerpat.entity.enumeration.CardType.*;

class CardTypeTest {
	@Test
	void getAdjustment() {
		assertEquals(new BigDecimal("0.9"), FOOD.getAdjustment());
		assertEquals(BigDecimal.ONE, DRUGSTORE.getAdjustment());
		assertEquals(new BigDecimal("1.35"), FUEL.getAdjustment());
	}
}