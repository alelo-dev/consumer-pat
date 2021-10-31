package br.com.alelo.consumer.consumerpat.exception;

public class CosumerException extends RuntimeException {
 
    private static final long serialVersionUID = 4928599035264976611L;
 
    public CosumerException(String message) {
        super(message);
    }
 
    public CosumerException(String msg, Throwable cause){
        super(msg, cause);
    }
}