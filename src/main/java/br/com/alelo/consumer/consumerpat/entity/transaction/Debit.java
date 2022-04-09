package br.com.alelo.consumer.consumerpat.entity.transaction;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.transaction.exceptions.InvalidDebitValueException;
import br.com.alelo.consumer.consumerpat.entity.transaction.exceptions.NegativeBalanceException;

public class Debit implements Transaction {

    private final double value;

    public Debit(double value, CardType type) {
        checkPreConditions(value);
        this.value = type.calcDiscountOrTax(value);
        checkPostConditions(this.value);
    }

    private static void checkPreConditions(double value) {
        if (value < 0) {
            throw new InvalidDebitValueException(value);
        }
    }

    private static void checkPostConditions(double value) {
        if (value < 0) {
            throw new NegativeBalanceException(value);
        }
    }

    @Override
    public double apply(double balance) {
        return balance - value;
    }
}
