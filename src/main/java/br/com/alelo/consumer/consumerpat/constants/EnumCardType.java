package br.com.alelo.consumer.consumerpat.constants;

public enum EnumCardType {
	
	FOOD_CARD(1l),
	DRUGSTORE_CARD(2l),
	FUEL_CARD(3l);
	
	private Long valueName;

	EnumCardType(Long valueName) {
        this.valueName = valueName;
    }

    public Long getValueName() {
        return valueName;
    }

}
