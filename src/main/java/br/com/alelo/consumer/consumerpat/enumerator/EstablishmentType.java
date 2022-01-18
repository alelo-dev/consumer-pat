package br.com.alelo.consumer.consumerpat.enumerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum EstablishmentType {
	FOOD_CARD(1, "Alimentação (food)"),
	DRUGSTORE_CARD(2, "Farmácia (DrugStore)") ,
	FUEL_CARD(3, "Posto de combustivel (Fuel)");
	
	int id;
	String name;
}
