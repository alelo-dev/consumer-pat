package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.util.EstablishmentType;

public interface BalanceAndBuyStrategy {
	
	public Consumer setBalance(int cardNumber, double value);

	public boolean buy(int cardNumber, double value);
	
	public EstablishmentType getEstablishmentType();
}
