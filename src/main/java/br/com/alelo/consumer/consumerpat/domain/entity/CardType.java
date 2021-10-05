package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardType {

	DRUGSTORE(0),
	FOOD(1),
	FUEL(2);
	
	private int id;

}
