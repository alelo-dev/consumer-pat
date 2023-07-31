package br.com.alelo.consumer.consumerpat.domain.consumer.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidAddress() {
        Address address = new Address("Main Street", 123, "Cityville", "Countryland", "12345");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertTrue(violations.isEmpty(), "Valid address should not have violations");
    }

    @Test
    void testBlankStreet() {
        Address address = new Address("", 123, "Cityville", "Countryland", "12345");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertFalse(violations.isEmpty(), "Blank street should have violations");
        assertEquals("street is required.", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testNullNumber() {
        Address address = new Address("Main Street", null, "Cityville", "Countryland", "12345");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertFalse(violations.isEmpty(), "Null number should have violations");
        assertEquals("number is required.", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testBlankCity() {
        Address address = new Address("Main Street", 123, "", "Countryland", "12345");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertFalse(violations.isEmpty(), "Blank city should have violations");
        assertEquals("city is required.", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testBlankCountry() {
        Address address = new Address("Main Street", 123, "Cityville", "", "12345");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertFalse(violations.isEmpty(), "Blank country should have violations");
        assertEquals("country is required.", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testBlankPortalCode() {
        Address address = new Address("Main Street", 123, "Cityville", "Countryland", "");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertFalse(violations.isEmpty(), "Blank portal code should have violations");
        assertEquals("portalCode is required.", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }
}
