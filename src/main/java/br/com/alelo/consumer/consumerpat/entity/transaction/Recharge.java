package br.com.alelo.consumer.consumerpat.entity.transaction;

import br.com.alelo.consumer.consumerpat.entity.transaction.exceptions.InvalidRechargeValueException;

public class Recharge implements Transaction {

    private final double value;

    public Recharge(double value) {
        checkPreConditions(value);
        this.value = value;
    }

    private static void checkPreConditions(double value) {
        if (value <= 0) {
            throw new InvalidRechargeValueException(value);
        }
    }

    @Override
    public double apply(double balance) {
        return balance + value;
    }
}
