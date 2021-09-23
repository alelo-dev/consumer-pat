package br.com.alelo.consumer.consumerpat.entity;

public enum CardType {
	
	FOOD(10, -1, 1), DRUG(0, 1, 2), FUEL(35, 1, 3);
	
	private double percent;
	private int factor;
	private int establishmentType;

	CardType(double percent, int factor, int establishmentType) {
		this.percent = percent;
		this.factor = factor;
		this.establishmentType = establishmentType;
	}
	
	public double calc(double value) {
		return value + (this.factor * (value * (this.percent/100)));
	}
	
	public int getEstablishmentType() {
		return this.establishmentType;
	}
}
