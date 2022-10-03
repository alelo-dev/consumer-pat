package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OperationType {

	CREDIT(1), DEBIT(2);

	private Integer value;

	public static OperationType getEnum(final Integer operationType) {
		return Arrays.stream(values()).filter(enumItem -> enumItem.getValue().equals(operationType)).findFirst()
				.orElse(null);
	}
}
