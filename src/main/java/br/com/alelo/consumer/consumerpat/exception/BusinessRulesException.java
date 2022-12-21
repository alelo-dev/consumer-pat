package br.com.alelo.consumer.consumerpat.exception;

public class BusinessRulesException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;
    
    public BusinessRulesException(String mensagem){
        super(mensagem);
    }
}
