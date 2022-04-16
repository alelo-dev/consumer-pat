package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerDataNotFoundException extends RuntimeException {

    public ConsumerDataNotFoundException() {
        super("Data Not Found");
    }


    public ConsumerDataNotFoundException(String message) {
        super(message);
    }

    public ConsumerDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


    public ConsumerDataNotFoundException(Throwable cause) {
        super(cause);
    }


    protected ConsumerDataNotFoundException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
