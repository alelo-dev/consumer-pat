package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    private Validator validator;

    @BeforeEach
    void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidPayment() {
        UUID id = UUID.randomUUID();
        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Meal";
        LocalDate buyDate = LocalDate.now();
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = new BigDecimal("50.0");

        Payment payment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);

        Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
        assertTrue(violations.isEmpty(), "Valid payment should not have violations");
    }

    @Test
    void testBlankProductDescription() {
        UUID id = UUID.randomUUID();
        Establishment establishment = new Establishment("Grocery Store", EstablishmentType.FOOD);
        String productDescription = "";
        LocalDate buyDate = LocalDate.now();
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = new BigDecimal("100.0");

        Payment payment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);

        Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
        assertFalse(violations.isEmpty(), "Blank product description should have violations");
        assertEquals("Product description is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testNullBuyDate() {
        UUID id = UUID.randomUUID();
        Establishment establishment = new Establishment("Coffee Shop", EstablishmentType.FOOD);
        String productDescription = "Coffee";
        LocalDate buyDate = null;
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = new BigDecimal("5.0");

        Payment payment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);

        Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
        assertFalse(violations.isEmpty(), "Null buy date should have violations");
        assertEquals("Buy date is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testNullCardNumber() {
        UUID id = UUID.randomUUID();
        Establishment establishment = new Establishment("Bookstore", null);
        String productDescription = "Book";
        LocalDate buyDate = LocalDate.now();
        CardNumber cardNumber = null;
        BigDecimal amount = new BigDecimal("20.0");

        Payment payment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);

        Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
        assertFalse(violations.isEmpty(), "Null card number should have violations");
        assertEquals("Card number is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testNullAmount() {
        UUID id = UUID.randomUUID();
        Establishment establishment = new Establishment("Gas Station", EstablishmentType.FUEL);
        String productDescription = "Fuel";
        LocalDate buyDate = LocalDate.now();
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = null;

        Payment payment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);

        Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
        assertFalse(violations.isEmpty(), "Null amount should have violations");
        assertEquals("Amount is required", violations.iterator().next().getMessage(), "Correct validation message should be present");
    }

    @Test
    void testAddPaymentStrategy() {
        UUID id = UUID.randomUUID();
        Establishment establishment = new Establishment("Restaurant", EstablishmentType.FOOD);
        String productDescription = "Meal";
        LocalDate buyDate = LocalDate.now();
        CardNumber cardNumber = new CardNumber("1234567812345678");
        BigDecimal amount = new BigDecimal("100.0");

        Payment payment = new Payment(establishment, productDescription, buyDate, cardNumber, amount);
        payment.addPaymentStrategy(CardType.FOOD);

        assertNotNull(payment.getPaymentStrategy(), "Payment strategy should not be null");
        assertEquals(PaymentStrategy.STRATEGY_FOOD, payment.getPaymentStrategy(), "Payment strategy should be STRATEGY_FOOD");
    }
}
