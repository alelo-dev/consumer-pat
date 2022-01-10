package br.com.alelo.consumer.consumerpat.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg) {
        super(status, msg);
        this.errors = errors;
    }

    public void addError(String fieldName, String mensagem){
        errors.add(new FieldMessage(fieldName, mensagem));
    }

}
