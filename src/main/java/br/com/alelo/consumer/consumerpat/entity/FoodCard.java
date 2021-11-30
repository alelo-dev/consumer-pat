package br.com.alelo.consumer.consumerpat.entity;

public class FoodCard implements Card {

	@Override
	public double calculate(double balance, double value) {
		Double cashback  = (value / 100) * 10;
        value = value - cashback;
        balance -= value;
		return balance;
	}

}
