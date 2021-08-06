package br.com.alelo.consumer.consumerpat.enuns;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum ContactTypeEnum {

	PHONE(1, "Telefone"),
	CELLPHONE(2, "Celular"),
	EMAIL(3, "E-mail");
	
	ContactTypeEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	private Integer code;
	private String description;
	
	public ContactTypeEnum to(Integer code) {
		return Arrays.stream(ContactTypeEnum.values())
				.filter( type -> type.code.equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported value: %s", code)));
	}
}
