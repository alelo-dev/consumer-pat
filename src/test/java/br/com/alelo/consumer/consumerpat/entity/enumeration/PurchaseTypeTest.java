package br.com.alelo.consumer.consumerpat.entity.enumeration;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.com.alelo.consumer.consumerpat.entity.enumeration.PurchaseType.*;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseTypeTest {

    @Test
    void getPercentageAdjustement() {
        assertEquals(BigDecimal.valueOf(0.9), FOOD.getPercentageAdjustement());
        assertEquals(BigDecimal.ONE, DRUGSTORE.getPercentageAdjustement());
        assertEquals(BigDecimal.valueOf(1.35), FUEL.getPercentageAdjustement());
    }
}