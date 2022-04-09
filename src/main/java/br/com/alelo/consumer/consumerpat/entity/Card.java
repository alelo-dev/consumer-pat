package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.transaction.Transaction;

public interface Card {

    String getNumber();

    double getBalance();

    CardType getType();

    void setBalance(double newBalance);

    default void add(Transaction transaction) {
        var newBalance = transaction.apply(getBalance());
        setBalance(newBalance);
    }
}
