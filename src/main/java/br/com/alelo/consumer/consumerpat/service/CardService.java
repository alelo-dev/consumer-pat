package br.com.alelo.consumer.consumerpat.service;

public interface CardService {

	void setBalance(final String cardNumber, final Double value);

	void buy(Integer establishmentType, String establishmentName, String cardNumber, String productDescription,
			Double value);

}
