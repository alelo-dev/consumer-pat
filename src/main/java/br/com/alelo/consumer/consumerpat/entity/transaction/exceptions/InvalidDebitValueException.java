package br.com.alelo.consumer.consumerpat.entity.transaction.exceptions;

public class InvalidDebitValueException extends RuntimeException {

    public InvalidDebitValueException(double value) {
        super("Invalid value for a debit value. Expected > 0 but received " + value);
    }
}
