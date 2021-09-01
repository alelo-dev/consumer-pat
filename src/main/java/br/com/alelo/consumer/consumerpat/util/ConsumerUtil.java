package br.com.alelo.consumer.consumerpat.util;

public class ConsumerUtil {

	public static double foodCardCashback(double value) {
		return (value / 100) * 10;
	}

	public static double fuelCardFee(double value) {
		return (value / 100) * 35;
	}
}
