package br.com.alelo.consumer.consumerpat.exception;

import java.util.List;

public class ApiValidationException extends Exception {

    private List<String> messages;

    public ApiValidationException() {
        super();
    }

    public ApiValidationException(String message) {
        super(message);
    }

    public ApiValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiValidationException(String message, List<String> messages) {
        super(message);
        this.messages = messages;
    }

    public ApiValidationException(List<String> messages) {
        super();
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
