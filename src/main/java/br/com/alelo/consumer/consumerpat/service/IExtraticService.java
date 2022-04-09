package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Extract;

public interface IExtraticService {
	
	public Extract buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value);

}
