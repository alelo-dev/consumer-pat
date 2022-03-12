package br.com.alelo.consumer.consumerpat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCard {

	FOOD("Food"), DRUGSTORE("Drugstore"), FUEL("Fuel");

	private String description;

}
