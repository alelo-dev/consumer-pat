package br.com.alelo.consumer.consumerpat.exception;

public class Error {
    
    private String userMessage;
    private String devMessage;
    
    public Error(String userMessage, String devMessage) {
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }

    public String getMsgUsuario() {
        return userMessage;
    }

    public String getMsgDesenvolvedor() {
        return devMessage;
    }

    
    
}
