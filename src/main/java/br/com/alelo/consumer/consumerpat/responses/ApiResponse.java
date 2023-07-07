package br.com.alelo.consumer.consumerpat.responses;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiResponse {
//    private int status;
    private String message;
    private Object data;
    private List<String> errors;
    private boolean isValid;

    public ApiResponse() {
        errors = new ArrayList<>();
    }

    public void addError(String error) {
        errors.add(error);
    }

    public boolean isValid(){
        return !hasErrors();
    }
    public void addErrors(BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    public List<String> getErrors() {
        return errors;
    }
}
