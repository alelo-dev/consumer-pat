package br.com.alelo.consumer.consumerpat.domain.consumer.entity;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumerTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidConsumer() {
        Consumer consumer = new Consumer(UUID.randomUUID(), "John Doe", "1234567890", LocalDate.of(1990, 1, 1));

        Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
        assertTrue(violations.isEmpty(), "Valid consumer should not have violations");
    }

    @Test
    void testBlankName() {
        Consumer consumer = new Consumer(UUID.randomUUID(), "", "1234567890", LocalDate.of(1990, 1, 1));

        Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
        assertFalse(violations.isEmpty(), "Blank name should have violations");
        assertEquals("Nome is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testBlankDocumentNumber() {
        Consumer consumer = new Consumer(UUID.randomUUID(), "John Doe", "", LocalDate.of(1990, 1, 1));

        Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
        assertFalse(violations.isEmpty(), "Blank document number should have violations");
        assertEquals("Document number is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testNullBirthDate() {
        Consumer consumer = new Consumer(UUID.randomUUID(), "John Doe", "1234567890", null);

        Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
        assertFalse(violations.isEmpty(), "Null birth date should have violations");
        assertEquals("Birth date is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testAddNullContact() {
        Consumer consumer = new Consumer(UUID.randomUUID(), "John Doe", "1234567890", LocalDate.of(1990, 1, 1));

        DomainException exception = assertThrows(DomainException.class, () -> consumer.addContact(null));
        assertEquals("Contact is required", exception.getMessage(), "Correct exception message should be thrown");
    }

    @Test
    void testAddNullAddress() {
        Consumer consumer = new Consumer(UUID.randomUUID(), "John Doe", "1234567890", LocalDate.of(1990, 1, 1));

        DomainException exception = assertThrows(DomainException.class, () -> consumer.addAddress(null));
        assertEquals("Address is required", exception.getMessage(), "Correct exception message should be thrown");
    }
}
