package br.com.alelo.consumer.consumerpat.entity;

public class FuelCard implements Card {

	@Override
	public double calculate(double balance, double value) {
		Double tax  = (value / 100) * 35;
        value = value + tax;
        balance -=value;
        return balance;
	}

}
