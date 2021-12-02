package br.com.alelo.consumer.consumerpat.exceptions;

import br.com.alelo.consumer.consumerpat.constants.ErrorCodeEnum;
import br.com.alelo.consumer.consumerpat.constants.ErrorMessages;

public class OperationException extends BaseException {

    public OperationException(
            final ErrorCodeEnum errorCodeEnum,
            final ErrorMessages errorMessages,
            final String message) {
        super(errorCodeEnum, errorMessages, message);
    }
}
