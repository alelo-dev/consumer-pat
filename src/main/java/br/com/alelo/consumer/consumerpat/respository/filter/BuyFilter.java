package br.com.alelo.consumer.consumerpat.respository.filter;

import lombok.Data;

@Data
public class BuyFilter {

	int establishmentType;
	String establishmentName;
	int cardNumber;
	String productDescription;
	double value;

}
