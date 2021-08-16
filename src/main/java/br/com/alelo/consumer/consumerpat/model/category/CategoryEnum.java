package br.com.alelo.consumer.consumerpat.model.category;

public enum CategoryEnum {
	FOOD(1), DRUGSTORE(2), FUEL(3);

	private Integer category;

	CategoryEnum(Integer category) {
		this.category = category;
	}

	public Integer getCategory() {
		return this.category;
	}
}
