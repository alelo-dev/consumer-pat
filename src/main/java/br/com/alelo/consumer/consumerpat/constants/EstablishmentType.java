package br.com.alelo.consumer.consumerpat.constants;

public enum EstablishmentType {

	FOOD(1), DRUGSTORE(2), FUEL(3);

	private final int id;

	EstablishmentType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
