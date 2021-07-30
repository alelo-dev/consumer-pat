package br.com.alelo.consumer.consumerpat.util;

import br.com.alelo.consumer.consumerpat.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static br.com.alelo.consumer.consumerpat.builder.ConsumerBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

class ConsumerValidationTest {

    @Test
    public void shouldNotFailWithNonNullConsumer() {
        assertDoesNotThrow(
                () -> ConsumerValidation.shouldNotBeNull(emptyConsumer()),
                "Expected shouldNotBeNull() does not throw, but it did"
        );
    }

    @Test
    public void shouldThrowValidationExceptionWithNullConsumer() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> ConsumerValidation.shouldNotBeNull(null),
                "Expected shouldNotBeNull() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains(Constants.CONSUMER));
    }

    @Test
    public void shouldNotFailWithNonNullConsumerId() {
        assertDoesNotThrow(
                () -> ConsumerValidation.shouldHaveId(1L),
                "Expected shouldHaveId() does not throw, but it did"
        );
    }

    @Test
    public void shouldThrowValidationExceptionWithNullConsumerId() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> ConsumerValidation.shouldHaveId(null),
                "Expected shouldHaveId() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains(Constants.CONSUMER_ID));
    }

    @Test
    public void shouldNotBeValidWithNullConsumer() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> ConsumerValidation.validate(null),
                "Expected validate() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains(Constants.CONSUMER));
    }

    @Test
    public void shouldNotBeValidWithEmptyCard() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> ConsumerValidation.validate(consumerWithEmptyCard()),
                "Expected validate() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains(Constants.CARD_NUMBER));
    }

    @Test
    public void shouldNotBeValidWithNullCard() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> ConsumerValidation.validate(consumerWithNullCard()),
                "Expected validate() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains(Constants.CARD));
    }

    @Test
    public void shouldBeValidWithNoCards() {
        assertDoesNotThrow(
                () -> ConsumerValidation.validate(emptyConsumer()),
                "Expected validate() does not throw, but it did"
        );
    }

    @Test
    public void shouldBeValidWithValidCard() {
        assertDoesNotThrow(
                () -> ConsumerValidation.validate(consumerWithValidCard()),
                "Expected validate() does not throw, but it did"
        );
    }
}