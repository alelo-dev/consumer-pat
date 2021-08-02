package br.com.alelo.consumer.consumerpat.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.alelo.consumer.consumerpat.enums.ContactType;

@Converter(autoApply = true)
public class ContactTypeConverter implements AttributeConverter<ContactType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ContactType type) {
        return type.getId();
    }

    @Override
    public ContactType convertToEntityAttribute(Integer id) {
        return ContactType.fromId(id);
    }

}
