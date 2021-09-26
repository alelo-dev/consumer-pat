package br.com.alelo.consumer.consumerpat.entity.enums;

public enum EstablishmentTypeEnum {

	FOOD("Alimentação"),
	DRUGSTORE("Farmácia"),
	FUEL("Posto de combustivel");
	
	private final String description;
	
	EstablishmentTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
