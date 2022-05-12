package br.com.alelo.consumer.consumerpat.validator;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CONSUMER_INVALID_DOCUMENT_NUMBER;
import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CONSUMER_MISSING_FIELD;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
public class ConsumerValidator implements java.util.function.Consumer<Consumer> {

    @Autowired
    MessageService messageService;

    @Override
    public void accept(Consumer consumer) {

        if (isBlank(consumer.getDocumentNumber())) {
            throw new CustomException(messageService.get(CONSUMER_MISSING_FIELD.getMessage(), "documentNumber"),
                    HttpStatus.BAD_REQUEST, CONSUMER_MISSING_FIELD.getCode());
        }

        final String documentNumberWithoutMask = consumer.getDocumentNumber().replaceAll("\\.", "").replaceAll("-", "");
        try {
            Long.parseLong(documentNumberWithoutMask);
        } catch (NumberFormatException nfe) {
            throw new CustomException(messageService.get(CONSUMER_INVALID_DOCUMENT_NUMBER.getMessage()),
                    HttpStatus.BAD_REQUEST, CONSUMER_INVALID_DOCUMENT_NUMBER.getCode());
        }

        if (isBlank(consumer.getName())) {
            throw new CustomException(messageService.get(CONSUMER_MISSING_FIELD.getMessage(), "name"),
                    HttpStatus.BAD_REQUEST, CONSUMER_MISSING_FIELD.getCode());
        }
    }
}
