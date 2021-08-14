package br.com.alelo.consumer.consumerpat.util;

public class Calculation {

	/**
	 * @param value
	 * @return
	 */
	public static double percentage(double value, double co) {
		return (value/100) * co;
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static double cashbackFood(double value) {
		 return value - percentage(value, 10);
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static double taxFuel(double value) {
		return value + percentage(value, 35);
	}
	
	
}
