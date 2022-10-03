package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


import br.com.alelo.consumer.consumerpat.exceptions.CardTypeEnumsNotFoundException;

@Getter
@AllArgsConstructor
public enum CardType {

	FOOD(1), DRUGSTORE(2), FUEL(3);

	private Integer value;

	public static CardType getEnum(final Integer cardType) {
		return Arrays.stream(values()).filter(enumItem -> enumItem.getValue().equals(cardType)).findFirst()
				.orElseThrow(CardTypeEnumsNotFoundException::new);
	}

}
