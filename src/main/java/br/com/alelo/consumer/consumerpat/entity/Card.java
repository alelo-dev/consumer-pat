package br.com.alelo.consumer.consumerpat.entity;

public interface Card {

    String getNumber();

    double getBalance();

    CardType getType();

    void setBalance(double newBalance);

    //TODO check negative value
    //TODO transaction
    default void recharge(double value) {
        var current = getBalance();
        setBalance(current + value);
    }

    //TODO unify in transaction
    //TODO check for positive values
    //TODO check for balance before
    default void minus(double value) {
        var current = getBalance();
        value = getType().calcDiscountOrTax(value);
        setBalance(current - value);
    }
}
