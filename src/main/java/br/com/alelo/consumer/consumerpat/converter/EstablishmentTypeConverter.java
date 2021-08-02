package br.com.alelo.consumer.consumerpat.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;

@Converter(autoApply = true)
public class EstablishmentTypeConverter implements AttributeConverter<EstablishmentType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EstablishmentType type) {
        return type.getId();
    }

    @Override
    public EstablishmentType convertToEntityAttribute(Integer id) {
        return EstablishmentType.fromId(id);
    }

}
