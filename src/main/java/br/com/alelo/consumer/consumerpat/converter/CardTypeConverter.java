package br.com.alelo.consumer.consumerpat.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.alelo.consumer.consumerpat.enums.CardType;

@Converter(autoApply = true)
public class CardTypeConverter implements AttributeConverter<CardType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CardType type) {
        return type.getId();
    }

    @Override
    public CardType convertToEntityAttribute(Integer id) {
        return CardType.fromId(id);
    }

}
