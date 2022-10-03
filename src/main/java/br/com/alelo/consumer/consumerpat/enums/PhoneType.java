package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

import br.com.alelo.consumer.consumerpat.exceptions.PhoneTypeEnumsException;

@Getter
@AllArgsConstructor
public enum PhoneType {

	MOBILE(1), RESIDENCE(2);

	private Integer value;

	public static PhoneType getEnum(final Integer phoneType) {
		return Arrays.stream(values()).filter(enumItem -> enumItem.getValue().equals(phoneType)).findFirst()
				.orElseThrow(PhoneTypeEnumsException::new);
	}
}
