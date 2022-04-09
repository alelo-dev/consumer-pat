package br.com.alelo.consumer.consumerpat.entity.transaction.exceptions;

public class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException(double balance) {
        super("It's not allowed negative balance. Balance with " + balance);
    }
}
