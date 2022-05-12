package br.com.alelo.consumer.consumerpat.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private HttpStatus code;

    private String internalErrorCode;

    public CustomException(final String message, final HttpStatus code, final String internalErrorCode) {
        super(message);
        this.code = code;
        this.internalErrorCode = internalErrorCode;
    }
}
