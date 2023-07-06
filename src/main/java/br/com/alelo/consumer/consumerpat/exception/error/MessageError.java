package br.com.alelo.consumer.consumerpat.exception.error;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MessageError {

    private List<String> errors = new ArrayList<>();

    public MessageError(String error) {
        this.errors.add(error);
    }

    public MessageError(List<ObjectError> allErrors) {
        allErrors.forEach(innerError -> errors.add(innerError.getDefaultMessage()));
    }
}
