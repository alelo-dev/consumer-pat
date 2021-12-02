package br.com.alelo.consumer.consumerpat.exceptions;

import br.com.alelo.consumer.consumerpat.constants.ErrorCodeEnum;
import br.com.alelo.consumer.consumerpat.constants.ErrorMessages;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private ErrorCodeEnum errorCodeEnum;
    private ErrorMessages errorMessages;

    public BaseException(final ErrorCodeEnum errorCodeEnum, final ErrorMessages errorMessages, final String message) {
        super(message);
        this.errorCodeEnum = errorCodeEnum;
        this.errorMessages = errorMessages;
    }

    public String getErrorMessage() {
        return errorMessages.getMessage();
    }

    public HttpStatus getHttpStatus() {
        return errorMessages.getHttpStatus();
    }
}
