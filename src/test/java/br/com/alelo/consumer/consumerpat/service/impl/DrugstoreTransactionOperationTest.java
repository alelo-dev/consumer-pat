package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.enumerated.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DrugstoreTransactionOperationTest {

    private DrugstoreTransactionOperation target;

    @BeforeEach
    public void init() {
        target = new DrugstoreTransactionOperation();
    }

    @Test
    public void shouldCalculateAndRoundFloorValue() {
        BigDecimal value = new BigDecimal("12.5365");
        BigDecimal expected = new BigDecimal("12.53");
        BigDecimal result = target.calculate(value);
        assertEquals(expected, result);
    }

    @Test
    public void shouldBeDrugstoreCard() {
        assertEquals(CardType.DRUGSTORE_CARD, target.getCardType());
    }
}