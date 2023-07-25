package br.com.alelo.consumer.consumerpat.domain.card.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CardNumberTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidCardNumber() {
        String validCardNumber = "1234567812345678";
        CardNumber cardNumber = new CardNumber(validCardNumber);

        Set<ConstraintViolation<CardNumber>> violations = validator.validate(cardNumber);
        assertTrue(violations.isEmpty(), "Valid card number should not have violations");
    }

    @Test
    void testBlankCardNumber() {
        String blankCardNumber = "";
        CardNumber cardNumber = new CardNumber(blankCardNumber);

        Set<ConstraintViolation<CardNumber>> violations = validator.validate(cardNumber);
        assertFalse(violations.isEmpty(), "Blank card number should have violations");
        assertEquals("Card number invalid", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testInvalidCardNumberFormat() {
        String invalidCardNumber = "12345abc67891234";
        CardNumber cardNumber = new CardNumber(invalidCardNumber);

        Set<ConstraintViolation<CardNumber>> violations = validator.validate(cardNumber);
        assertFalse(violations.isEmpty(), "Invalid card number format should have violations");
        assertEquals("Card number invalid", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testNullCardNumber() {
        String nullCardNumber = null;
        CardNumber cardNumber = new CardNumber(nullCardNumber);

        Set<ConstraintViolation<CardNumber>> violations = validator.validate(cardNumber);
        assertFalse(violations.isEmpty(), "Null card number should have violations");
        assertEquals("Card number is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }
}
