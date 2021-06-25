package br.com.alelo.consumer.consumerpat.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationUtil {

	public static double calculateTotalValue(double baseValue, double extraValuePercentage) {
    	Double extra = new BigDecimal(baseValue / 100).multiply(new BigDecimal(extraValuePercentage)).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        return baseValue + extra;
    }
}
