package br.com.alelo.consumer.consumerpat.controller.exception;

public class ErrorGenericException extends RuntimeException {
    public ErrorGenericException(String msg){
        super(msg);
    }
}
