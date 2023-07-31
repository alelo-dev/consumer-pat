package br.com.alelo.consumer.consumerpat.domain.consumer.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

public class ConsumerTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidConsumer() {
        Consumer consumer = new Consumer(
                "John Doe",
                "123456789",
                LocalDate.of(1990, 1, 1),
                new Contact("123456789", "987654321", "111111111", "john@example.com"),
                new Address("123 Main St", 10, "City", "Country", "12345")
        );

        Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidConsumerWithMissingName() {
        Consumer consumer = new Consumer(
                null,
                "123456789",
                LocalDate.of(1990, 1, 1),
                new Contact("123456789", "987654321", "111111111", "john@example.com"),
                new Address("123 Main St", 10, "City", "Country", "12345")
        );

        Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<Consumer> violation = violations.iterator().next();
        Assertions.assertEquals("name is required", violation.getMessage());
    }

    @Test
    public void testInvalidConsumerWithInvalidEmail() {
        Consumer consumer = new Consumer(
                "John Doe",
                "123456789",
                LocalDate.of(1990, 1, 1),
                new Contact("123456789", "987654321", "111111111", "invalid_email"),
                new Address("123 Main St", 10, "City", "Country", "12345")
        );

        Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<Consumer> violation = violations.iterator().next();
        Assertions.assertEquals("Must be a valid email address", violation.getMessage());
    }

    @Test
    public void testInvalidConsumerWithMissingDocumentNumber() {
        Consumer consumer = new Consumer(
                "John Doe",
                null,
                LocalDate.of(1990, 1, 1),
                new Contact("123456789", "987654321", "111111111", "john@example.com"),
                new Address("123 Main St", 10, "City", "Country", "12345")
        );

        Set<ConstraintViolation<Consumer>> violations = validator.validate(consumer);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<Consumer> violation = violations.iterator().next();
        Assertions.assertEquals("documentNumber is required", violation.getMessage());
    }
}

