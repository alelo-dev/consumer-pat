package br.com.alelo.consumer.consumerpat.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

/**
 * Service to format values
 *
 * @author mcrj
 */
@Service
@Scope("singleton")
public class BigDecimalUtil {

    /**
     * Returns a value formatted with two decimal places and rounded
     *
     * @param value - Value to be formatted
     * @return {@link BigDecimal}
     */
    public BigDecimal getValueWithScale(BigDecimal value) {
        return value.setScale(2, HALF_UP);
    }
}
