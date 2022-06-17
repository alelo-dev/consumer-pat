package br.com.alelo.consumer.consumerpat.util;

public enum EstablishmentType {
	
	FOOD(1, "Alimentação"),
	DRUGSTORE(2, "Farmácia"),
	FUEL(3, "Posto de combustivel");
	
	int code;
	String description;
	
	EstablishmentType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
