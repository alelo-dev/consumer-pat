package br.com.alelo.consumer.consumerpat.enuns;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum CardTypeEnum {
	
	DRUGSTORE(1, "Farmácia"),
	FOOD(2, "Alimentação"),
	FUEL(3, "Combustível");
	
	CardTypeEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	private Integer code;
	private String description;
	
	public CardTypeEnum to(Integer code) {
		return Arrays.stream(CardTypeEnum.values())
				.filter( type -> type.code.equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported value: %s", code)));
	}
}
