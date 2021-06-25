package br.com.alelo.consumer.consumerpat.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.alelo.consumer.consumerpat.enums.EstablishmentTypeEnum;

public class CalculationUtil {

	public static double calculateTotalValue(double baseValue, EstablishmentTypeEnum establishmentType) {
    	Double extra = new BigDecimal(baseValue / 100).multiply(new BigDecimal(establishmentType.getExtraValuePercentage())).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        return baseValue + extra;
    }
}
