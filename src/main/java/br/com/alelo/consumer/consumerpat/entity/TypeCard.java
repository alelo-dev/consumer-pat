package br.com.alelo.consumer.consumerpat.entity;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCard {

	FOOD(1),
	DRUGSTORE(2),
	FUEL(3);

	private Integer value;

	public static TypeCard getEnum(final Integer type) throws Exception {
		return Arrays.stream(values()).filter(item -> item.getValue().equals(type)).findFirst().orElseThrow(Exception::new);
	}

}
