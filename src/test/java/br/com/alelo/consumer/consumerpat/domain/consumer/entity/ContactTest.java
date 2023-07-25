package br.com.alelo.consumer.consumerpat.domain.consumer.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidContact() {
        Contact contact = new Contact("+123456789", "987654321", "12345678", "test@example.com");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        assertTrue(violations.isEmpty(), "Valid contact should not have violations");
    }

    @Test
    void testInvalidEmail() {
        Contact contact = new Contact("+123456789", "987654321", "12345678", "invalidemail");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        assertFalse(violations.isEmpty(), "Invalid email should have violations");
        assertEquals("Must be a valid email address", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testNullEmail() {
        Contact contact = new Contact("+123456789", "987654321", "12345678", null);

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        assertTrue(violations.isEmpty(), "Null email is allowed");
    }
}
