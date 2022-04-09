package br.com.alelo.consumer.consumerpat.entity.transaction.exceptions;

public class InvalidRechargeValueException extends RuntimeException {

    public InvalidRechargeValueException(double value) {
        super("Invalid value for a debit value. Expected > 0 but received " + value);
    }
}
