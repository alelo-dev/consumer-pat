package br.com.alelo.consumer.consumerpat.exception;

public class ProductNotAllowedException extends RuntimeException {

    public ProductNotAllowedException() {
    }

    public ProductNotAllowedException(String message) {
        super(message);
    }

    public ProductNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotAllowedException(Throwable cause) {
        super(cause);
    }

    public ProductNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
