package br.com.alelo.consumer.pat.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component(FuelChangeValue.NAME)
public class FuelChangeValue implements ValueChanger {

    public static final String NAME = "FUEL";
    public static final BigDecimal AMOUNT = BigDecimal.valueOf(35);

    @Override
    public BigDecimal change(final BigDecimal value) {
        BigDecimal tax = percentage(value, AMOUNT);
        return value.add(tax);
    }

}