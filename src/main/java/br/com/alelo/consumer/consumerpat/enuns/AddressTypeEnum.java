package br.com.alelo.consumer.consumerpat.enuns;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum AddressTypeEnum {
	
	HOME(1, "Endereco Residencial"),
	BUSSINESS(2, "Endereço Comercial");
	
	AddressTypeEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	private Integer code;
	private String description;
	
	public AddressTypeEnum to(Integer code) {
		return Arrays.stream(AddressTypeEnum.values())
				.filter( type -> type.code.equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported value: %s", code)));
	}
}
