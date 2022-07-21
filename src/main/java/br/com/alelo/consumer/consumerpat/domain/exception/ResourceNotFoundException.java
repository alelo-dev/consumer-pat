package br.com.alelo.consumer.consumerpat.domain.exception;

import br.com.alelo.consumer.consumerpat.domain.util.MessageUtil;

public class ResourceNotFoundException extends ConsumerApplicationException {

    public ResourceNotFoundException() {

    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(
            String message,
            Throwable cause,
            boolean enableSupression,
            boolean writeableStackTrace) {
        super(message, cause, enableSupression, writeableStackTrace);
    }

    public static ResourceNotFoundException ofConsumerNotFoundException() {
        return new ResourceNotFoundException(MessageUtil.MSG_CONSUMER_NOT_FOUND);
    }

}
