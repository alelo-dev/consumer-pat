package br.com.alelo.consumer.consumerpat.domain.util;

import br.com.alelo.consumer.consumerpat.domain.converter.BigDecimalConverter;

import java.math.BigDecimal;

/**
 * Classe utilitária criada com o intuito de facilitar a realização de cálculos,
 * principalmente com o tipo BigDecimal.
 */
@SuppressWarnings("unused")
public final class BigDecimalCalculator {

    private BigDecimal value;

    public BigDecimalCalculator(BigDecimal value) {
        this.value = BigDecimalConverter.convertToBigDecimal(value);
    }

    public BigDecimalCalculator(Double value) {
        this.value = BigDecimalConverter.convertToBigDecimal(value);
    }

    public static BigDecimalCalculator of(BigDecimal value) {
        return new BigDecimalCalculator(value);
    }

    public static BigDecimalCalculator of(Double value) {
        return new BigDecimalCalculator(value);
    }

    public BigDecimal getResult() {
        return value;
    }

    public BigDecimalCalculator add(BigDecimal x) {
        BigDecimal bx = BigDecimalConverter.convertToBigDecimal(x);
        this.value = this.value.add(bx);
        return this;
    }

    public BigDecimalCalculator add(Double x) {
        BigDecimal bx = BigDecimalConverter.convertToBigDecimal(x);
        this.value = this.value.add(bx);
        return this;
    }

    public BigDecimalCalculator subtract(BigDecimal x) {
        BigDecimal bx = BigDecimalConverter.convertToBigDecimal(x);
        this.value = this.value.subtract(bx);
        return this;
    }

    public BigDecimalCalculator subtract(Double x) {
        BigDecimal bx = BigDecimalConverter.convertToBigDecimal(x);
        this.value = this.value.subtract(bx);
        return this;
    }

    public BigDecimalCalculator divide(BigDecimal x) {
        BigDecimal bx = BigDecimalConverter.convertToBigDecimal(x);
        this.value = this.value.divide(bx);
        return this;
    }

    public BigDecimalCalculator divide(Double x) {
        BigDecimal bx = BigDecimalConverter.convertToBigDecimal(x);
        this.value = this.value.divide(bx);
        return this;
    }

    public BigDecimalCalculator multiply(BigDecimal x) {
        BigDecimal bx = BigDecimalConverter.convertToBigDecimal(x);
        this.value = this.value.multiply(bx);
        return this;
    }

    public BigDecimalCalculator multiply(Double x) {
        BigDecimal bx = BigDecimalConverter.convertToBigDecimal(x);
        this.value = this.value.multiply(bx);
        return this;
    }

}
