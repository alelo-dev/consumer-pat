package br.com.alelo.consumer.consumerpat.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.com.alelo.consumer.consumerpat.utils.BigDecimalUtils.isGreaterThanZero;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BigDecimalUtilsTest {

    @Test
    void isGreaterThanZero_WithNullValue_ShouldReturnFalse() {
        assertFalse(isGreaterThanZero(null));
    }

    @Test
    void isGreaterThanZero_WithLessThanZeroValue_ShouldReturnFalse() {
        assertFalse(isGreaterThanZero(BigDecimal.ONE.negate()));
    }

    @Test
    void isGreaterThanZero_WithZeroValue_ShouldReturnFalse() {
        assertFalse(isGreaterThanZero(BigDecimal.ZERO));
    }

    @Test
    void isGreaterThanZero_WithGreaterThanZeroValue_ShouldReturnTrue() {
        assertTrue(isGreaterThanZero(BigDecimal.ONE));
    }
}