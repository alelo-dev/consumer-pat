package br.com.alelo.consumer.consumerpat.model.converter;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.alelo.consumer.consumerpat.emun.TypeCard;

@Converter
public class TypeCardConverter implements AttributeConverter<TypeCard, Integer>{

	@Override
	public Integer convertToDatabaseColumn(TypeCard attribute) {
		return Optional.ofNullable(attribute)
				.map(TypeCard::getValue)
				.orElseThrow(() -> new IllegalArgumentException(attribute + " não suportado."));
	}

	@Override
	public TypeCard convertToEntityAttribute(Integer dbData) {
	    return Stream.of(TypeCard.values())
	            .filter(i -> i.getValue().equals(dbData))
	            .findFirst()
	            .orElse(null);
	}

}
