package br.com.alelo.consumer.consumerpat.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiResponse {
//    private int status;
    private String message;
    private Object data;
    private List<String> errors;

    public ApiResponse() {
        errors = new ArrayList<>();
    }

    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }
}
