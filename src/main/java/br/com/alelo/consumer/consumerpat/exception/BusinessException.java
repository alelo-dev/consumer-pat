package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class BusinessException extends RuntimeException {
   
	private static final long serialVersionUID = -8006760629397176660L;
	private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public BusinessException( String message, String fieldName, String fieldValue) {
        super(String.format("%s Business Exception %s : '%s'", message, fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;        
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
