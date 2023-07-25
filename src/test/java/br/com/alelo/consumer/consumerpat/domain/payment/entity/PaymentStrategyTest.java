package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class PaymentStrategyTest {

    @Test
    void testGetStrategyFood() {
        CardType cardType = CardType.FOOD;
        PaymentStrategy strategy = PaymentStrategy.getStrategy(cardType);
        assertEquals(PaymentStrategy.STRATEGY_FOOD, strategy, "Strategy should be STRATEGY_FOOD");
    }

    @Test
    void testGetStrategyDrugstore() {
        CardType cardType = CardType.DRUGSTORE;
        PaymentStrategy strategy = PaymentStrategy.getStrategy(cardType);
        assertEquals(PaymentStrategy.STRATEGY_DRUGSTORE, strategy, "Strategy should be STRATEGY_DRUGSTORE");
    }

    @Test
    void testGetStrategyFuel() {
        CardType cardType = CardType.FUEL;
        PaymentStrategy strategy = PaymentStrategy.getStrategy(cardType);
        assertEquals(PaymentStrategy.STRATEGY_FUEL, strategy, "Strategy should be STRATEGY_FUEL");
    }

    @Test
    void testGetStrategyInvalid() {
        CardType cardType = null;
        DomainException exception = assertThrows(DomainException.class, () -> PaymentStrategy.getStrategy(cardType));
        assertEquals("Payment type not permitted.", exception.getMessage(), "Correct exception message should be thrown");
    }

    @Test
    void testApplyDiscountRateFood() {
        CardType cardType = CardType.FOOD;
        BigDecimal amount = new BigDecimal("100.0");
        BigDecimal expectedAmount = new BigDecimal("90.0");

        PaymentStrategy strategy = PaymentStrategy.getStrategy(cardType);
        BigDecimal discountedAmount = strategy.applyDiscountRate(cardType, amount);

        assertEquals(expectedAmount, discountedAmount, "Discounted amount should be correct for STRATEGY_FOOD");
    }

    @Test
    void testApplyDiscountRateDrugstore() {
        CardType cardType = CardType.DRUGSTORE;
        BigDecimal amount = new BigDecimal("100.0");
        BigDecimal expectedAmount = amount;

        PaymentStrategy strategy = PaymentStrategy.getStrategy(cardType);
        BigDecimal discountedAmount = strategy.applyDiscountRate(cardType, amount);

        assertEquals(expectedAmount, discountedAmount, "Discounted amount should be correct for STRATEGY_DRUGSTORE");
    }

    @Test
    void testApplyDiscountRateFuel() {
        CardType cardType = CardType.FUEL;
        BigDecimal amount = new BigDecimal("100.0");
        BigDecimal expectedAmount = new BigDecimal("65.0");

        PaymentStrategy strategy = PaymentStrategy.getStrategy(cardType);
        BigDecimal discountedAmount = strategy.applyDiscountRate(cardType, amount);

        assertEquals(expectedAmount, discountedAmount, "Discounted amount should be correct for STRATEGY_FUEL");
    }
}
