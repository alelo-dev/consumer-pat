package br.com.alelo.consumer.consumerpat.util;

import org.springframework.context.i18n.LocaleContextHolder;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class NumberUtil {

    public static Boolean isPositive(final BigDecimal bigDecimal) {
        return !isNull(bigDecimal) && BigDecimal.ZERO.compareTo(bigDecimal) < 0;
    }

    public static Boolean isNegative(final BigDecimal bigDecimal) {
        return !isNull(bigDecimal) && bigDecimal.signum() == -1;
    }

    public static Boolean isNull(final BigDecimal bigDecimal) {
        return bigDecimal == null;
    }

    public static Boolean isPositive(final Integer integer) {
        return !isNull(integer) && integer > 0;
    }

    public static Boolean isNull(final Integer integer) {
        return integer == null;
    }

    public static Boolean isPositive(final Long long1) {
        return !isNull(long1) && long1 > 0L;
    }

    public static Boolean isNull(final Long long1) {
        return long1 == null;
    }


    public static String getBigDecimalFormatted(final BigDecimal bigDecimal, final int decimalPlaces, final boolean currency) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(LocaleContextHolder.getLocale());
        if (currency) {
            numberFormat = NumberFormat.getCurrencyInstance(LocaleContextHolder.getLocale());
        }
        numberFormat.setGroupingUsed(true);
        numberFormat.setMinimumFractionDigits(decimalPlaces);
        numberFormat.setMaximumFractionDigits(decimalPlaces);
        return numberFormat.format(bigDecimal);
    }
}
