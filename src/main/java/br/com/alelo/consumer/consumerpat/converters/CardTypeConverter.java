package br.com.alelo.consumer.consumerpat.converters;

import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.isNull;


@Converter
public class CardTypeConverter implements AttributeConverter<CardType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CardType cardType) {
        if (isNull(cardType)) {
            return null;
        }
        return cardType.getCardTypeId();
    }

    @Override
    public CardType convertToEntityAttribute(Integer cardTypeID) {
        if (isNull(cardTypeID)) {
            return null;
        }

        return CardType.getById(cardTypeID);
    }
}