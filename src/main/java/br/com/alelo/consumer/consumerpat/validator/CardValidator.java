package br.com.alelo.consumer.consumerpat.validator;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.service.MessageService;
import br.com.alelo.consumer.consumerpat.utils.MaskUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CARD_INVALID_NUMBER;
import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CARD_MISSING_FIELD;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
public class CardValidator implements Consumer<Card> {

    @Autowired
    MessageService messageService;

    @Override
    public void accept(Card card) {

        if (isBlank(card.getNumber())) {
            throw new CustomException(messageService.get(CARD_MISSING_FIELD.getMessage(), "number"),
                    HttpStatus.BAD_REQUEST, CARD_MISSING_FIELD.getCode());
        }

        final String cardNumberWithoutMask = MaskUtils.removeCardNumberMask(card.getNumber());
        try {
            Long.parseLong(cardNumberWithoutMask);
        } catch (NumberFormatException nfe) {
            throw new CustomException(messageService.get(CARD_INVALID_NUMBER.getMessage()),
                    HttpStatus.BAD_REQUEST, CARD_INVALID_NUMBER.getCode());
        }

        if (isNull(card.getType())) {
            throw new CustomException(messageService.get(CARD_MISSING_FIELD.getMessage(), "type"),
                    HttpStatus.BAD_REQUEST, CARD_MISSING_FIELD.getCode());
        }

    }
}