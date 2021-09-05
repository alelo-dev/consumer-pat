package br.com.alelo.consumer.pat.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component(DrugstoreValueChanger.NAME)
public class DrugstoreValueChanger implements ValueChanger {

    public static final String NAME = "DRUGSTORE";

    @Override
    public BigDecimal change(final BigDecimal value) {
        return value;
    }

}
