package br.com.alelo.consumer.consumerpat.entity;

public class DrugstoreCard implements Card {

	@Override
	public double calculate(double balance, double value) {
		balance -= value;
		return balance;
	}

}
