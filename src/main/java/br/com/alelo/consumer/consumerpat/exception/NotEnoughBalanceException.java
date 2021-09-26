package br.com.alelo.consumer.consumerpat.exception;

import lombok.Getter;

public class NotEnoughBalanceException extends RuntimeException {

    @Getter
    private final double currentBalance;

    public NotEnoughBalanceException(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public NotEnoughBalanceException(String message, double currentBalance) {
        super(message);
        this.currentBalance = currentBalance;
    }

    public NotEnoughBalanceException(String message, Throwable cause, double currentBalance) {
        super(message, cause);
        this.currentBalance = currentBalance;
    }

    public NotEnoughBalanceException(Throwable cause, double currentBalance) {
        super(cause);
        this.currentBalance = currentBalance;
    }

    public NotEnoughBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, double currentBalance) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.currentBalance = currentBalance;
    }

}
