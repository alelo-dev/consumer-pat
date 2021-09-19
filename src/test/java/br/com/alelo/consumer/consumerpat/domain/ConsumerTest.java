package br.com.alelo.consumer.consumerpat.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.alelo.consumer.consumerpat.Mocks;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;

class ConsumerTest {

	@Test
	void testValidated() {
		Consumer consumer = Mocks.getConsumer();
		consumer.validated();
		assertNotNull(consumer);
		assertNotNull(consumer.getName());
	}

	@Test
	void testValidatedError() {
		try {
			Consumer consumer = new Consumer();
			consumer.validated();
		} catch (BusinessException e) {
			assertEquals("error validating the consumer", e.getMessage());
		}

	}

	@Test
	void testValidatedInvalidBirthDate() {
		try {
			Consumer consumer = Mocks.getConsumer();
			consumer.setBirthDate(LocalDate.now().plusDays(1));
			consumer.validated();
		} catch (BusinessException e) {
			assertEquals("error validating the consumer", e.getMessage());
		}

	}

	@Test
	void testAddCard() {
		Consumer consumer = Mocks.getConsumer();

		Card card = Mocks.getCard(CardType.FOOD, BigDecimal.ONE);
		consumer.addCard(card);
		assertEquals(1, consumer.getCards().size());
	}

	@Test
	void testAddNullCard() {

		try {
			Consumer consumer = Mocks.getConsumer();
			consumer.addCard(null);
		} catch (BusinessException e) {
			assertEquals(Consumer.CARD_IS_NULL.getMessage(), e.getMessage());
		}

	}

}
