package br.com.alelo.consumer.consumerpat.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class BigDecimalUtilTest {

    @Autowired
    private BigDecimalUtil util;

    private static final BigDecimal VALUE_NOT_FORMATTED = BigDecimal.TEN;
    private static final BigDecimal VALUE_FORMATTED = BigDecimal.TEN.setScale(2, HALF_UP);

    @Test
    void getValueWithScale_WhenValueIsFormatted_ThenSuccess() {
        assertEquals(VALUE_FORMATTED, util.getValueWithScale(BigDecimal.TEN));
    }

    @Test
    void getValueWithScale_WhenValueIsNotFormatted_ThenNotSuccess() {
        assertNotEquals(VALUE_NOT_FORMATTED, util.getValueWithScale(BigDecimal.TEN));
    }
}
