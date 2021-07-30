package br.com.alelo.consumer.consumerpat.util;

import br.com.alelo.consumer.consumerpat.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static br.com.alelo.consumer.consumerpat.builder.CardBuilder.emptyCard;
import static br.com.alelo.consumer.consumerpat.builder.CardBuilder.validCard;
import static org.junit.jupiter.api.Assertions.*;

class CardValidationTest {

    @Test
    public void shouldNotBeValidWithNullCard() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> CardValidation.validate(null),
                "Expected validate() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains(Constants.CARD));
    }

    @Test
    public void shouldNotBeValidWithEmptyCard() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> CardValidation.validate(emptyCard()),
                "Expected validate() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains(Constants.CARD_NUMBER));
    }

    @Test
    public void shouldBeValidWithValidCard() {
        assertDoesNotThrow(
                () -> CardValidation.validate(validCard()),
                "Expected validate() does not throw, but it did"
        );
    }
}