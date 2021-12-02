package br.com.alelo.consumer.consumerpat.exceptions;

import br.com.alelo.consumer.consumerpat.constants.ErrorCodeEnum;
import br.com.alelo.consumer.consumerpat.constants.ErrorMessages;

public class PurchaseException extends BaseException {

    public PurchaseException(
            final ErrorCodeEnum errorCodeEnum,
            final ErrorMessages errorMessages,
            final String message) {
        super(errorCodeEnum, errorMessages, message);
    }
}
