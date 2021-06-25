package br.com.alelo.consumer.consumerpat.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EStablishmentType {
	FOOD(1,"Alientação"),
	DRUGSTORE(2,"Farmácia"),
	FUEL(3,"Postos de combustível");

	private int id;
	private String name;
	
	public static EStablishmentType findById(final Long id) {
		return Arrays.stream(EStablishmentType.values())
			.filter(status -> status.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("Tipo de estabelecimento inválido!"));
	}
}
