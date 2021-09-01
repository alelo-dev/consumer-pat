package br.com.alelo.consumer.consumerpat.strategy;

public enum ConsumerType {
	FOOD(1), DRUGSTORE(2), FUEL(3);

	private int value;

	private ConsumerType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}