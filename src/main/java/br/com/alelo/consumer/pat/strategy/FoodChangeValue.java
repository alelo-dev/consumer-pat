package br.com.alelo.consumer.pat.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component(FoodChangeValue.NAME)
public class FoodChangeValue implements ValueChanger {

    public static final String NAME = "FOOD";
    public static final BigDecimal AMOUNT = BigDecimal.TEN;

    @Override
    public BigDecimal change(final BigDecimal value) {
        BigDecimal cashback = percentage(value, AMOUNT);
        return value.subtract(cashback);
    }

}
