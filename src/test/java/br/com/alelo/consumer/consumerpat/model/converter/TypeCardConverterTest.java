package br.com.alelo.consumer.consumerpat.model.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.alelo.consumer.consumerpat.emun.TypeCard;

class TypeCardConverterTest {

	@Test
	void testConvertToDatabaseColumn() {
		TypeCardConverter cardConverter = new TypeCardConverter();
		assertEquals(1, cardConverter.convertToDatabaseColumn(TypeCard.FOOD));
		assertEquals(2, cardConverter.convertToDatabaseColumn(TypeCard.DRUG));
		assertEquals(3, cardConverter.convertToDatabaseColumn(TypeCard.FUEL));
	}

	@Test
	void testConvertToEntityAttribute() {
		TypeCardConverter cardConverter = new TypeCardConverter();
		assertEquals(TypeCard.FOOD, cardConverter.convertToEntityAttribute(1));
		assertEquals(TypeCard.DRUG, cardConverter.convertToEntityAttribute(2));
		assertEquals(TypeCard.FUEL, cardConverter.convertToEntityAttribute(3));
		
		cardConverter.convertToEntityAttribute(4);
		
	}

}
