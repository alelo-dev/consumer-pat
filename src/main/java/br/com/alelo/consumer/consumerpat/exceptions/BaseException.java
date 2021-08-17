package br.com.alelo.consumer.consumerpat.exceptions;

public abstract class BaseException extends RuntimeException {
    public abstract AppErrors error();
}
