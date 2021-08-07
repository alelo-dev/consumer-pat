package br.com.alelo.consumer.consumerpat.enuns;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum CardOperationEnum {
	
	DEBIT(1, "Débito"),
	CREDIT(2, "Crédito");
	
	CardOperationEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	private Integer code;
	private String description;
	
	public CardOperationEnum to(Integer code) {
		return Arrays.stream(CardOperationEnum.values())
				.filter( type -> type.code.equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported value: %s", code)));
	}
}
