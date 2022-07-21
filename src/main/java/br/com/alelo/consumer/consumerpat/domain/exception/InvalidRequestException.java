package br.com.alelo.consumer.consumerpat.domain.exception;

import br.com.alelo.consumer.consumerpat.domain.util.MessageUtil;

public class InvalidRequestException extends ConsumerApplicationException {

    public InvalidRequestException() {

    }

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(Throwable cause) {
        super(cause);
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestException(
            String message,
            Throwable cause,
            boolean enableSupression,
            boolean writeableStackTrace) {
        super(message, cause, enableSupression, writeableStackTrace);
    }

    public static InvalidRequestException of(String message) {
        return new InvalidRequestException(message);
    }

    public static InvalidRequestException ofNoValidRequestDataFound() {
        return new InvalidRequestException(MessageUtil.MSG_NO_VALID_REQUEST_DATA_FOUND);
    }

    public static InvalidRequestException ofInvalidFieldException(String field, String message) {
        return new InvalidRequestException(String.format(MessageUtil.MSG_INVALID_FIELD, field, message));
    }

    public static InvalidRequestException ofNotNullFieldException(String field) {
        return new InvalidRequestException(String.format(MessageUtil.MSG_INVALID_FIELD, field, "cannot be null!"));
    }

    public static InvalidRequestException ofNotBlankFieldException(String field) {
        return new InvalidRequestException(String.format(MessageUtil.MSG_INVALID_FIELD, field, "cannot be blank!"));
    }

    public static InvalidRequestException ofRequiredFieldException(String field) {
        return new InvalidRequestException(String.format(MessageUtil.MSG_REQUIRED_FIELD, field));
    }

    public static InvalidRequestException ofInvalidValueFieldException(String field) {
        return new InvalidRequestException(String.format(MessageUtil.MSG_INVALID_VALUE_FIELD, field));
    }

    public static InvalidRequestException ofConsumerAlreadyRegisteredException() {
        return new InvalidRequestException(MessageUtil.MSG_CONSUMER_ALREADY_REGISTERED);
    }

    public static InvalidRequestException ofCardBalanceCannotBeChangedException() {
        return new InvalidRequestException(MessageUtil.MSG_CARD_BALANCE_CANNOT_BE_CHANGED);
    }

    public static InvalidRequestException ofFailedToDeleteConsumerException() {
        return new InvalidRequestException(MessageUtil.MSG_FAILED_TO_DELETE_CONSUMER);
    }

    public static InvalidRequestException ofBenefitCardNotFound() {
        return new InvalidRequestException(MessageUtil.MSG_BENEFIT_CARD_NOT_FOUND);
    }

}
