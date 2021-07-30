package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.enumerated.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTransactionOperationTest {

    private FoodTransactionOperation target;

    @BeforeEach
    public void init() {
        target = new FoodTransactionOperation();
    }

    @Test
    public void shouldCalculate() {
        BigDecimal value = new BigDecimal("100.00");
        BigDecimal expected = new BigDecimal("90.00");
        BigDecimal result = target.calculate(value);
        assertEquals(expected, result);
    }

    @Test
    public void shouldBeFoodCard() {
        assertEquals(CardType.FOOD_CARD, target.getCardType());
    }
}