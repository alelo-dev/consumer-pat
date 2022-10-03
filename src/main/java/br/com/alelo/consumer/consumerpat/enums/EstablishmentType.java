package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

import br.com.alelo.consumer.consumerpat.exceptions.EstablishmentTypeEnumsException;

@Getter
@AllArgsConstructor
public enum EstablishmentType {

	FOOD(1), DRUGSTORE(2), FUEL(3);

	private Integer value;

	public static EstablishmentType getEnum(final Integer establishmentType) {
		return Arrays.stream(values()).filter(enumItem -> enumItem.getValue().equals(establishmentType)).findFirst()
				.orElseThrow(EstablishmentTypeEnumsException::new);
	}

}
