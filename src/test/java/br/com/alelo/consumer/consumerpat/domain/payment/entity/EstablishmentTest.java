package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EstablishmentTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidEstablishment() {
        EstablishmentType establishmentType = EstablishmentType.FOOD;
        Establishment establishment = new Establishment("Restaurant", establishmentType);

        Set<ConstraintViolation<Establishment>> violations = validator.validate(establishment);
        assertTrue(violations.isEmpty(), "Valid establishment should not have violations");
    }

    @Test
    void testBlankName() {
        EstablishmentType establishmentType = EstablishmentType.DRUGSTORE;
        Establishment establishment = new Establishment("", establishmentType);

        Set<ConstraintViolation<Establishment>> violations = validator.validate(establishment);
        assertFalse(violations.isEmpty(), "Blank name should have violations");
        assertEquals("Establishment name is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testNullType() {
        Establishment establishment = new Establishment("Store", null);

        Set<ConstraintViolation<Establishment>> violations = validator.validate(establishment);
        assertFalse(violations.isEmpty(), "Null type should have violations");
        assertEquals("Establishment type is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }
}
