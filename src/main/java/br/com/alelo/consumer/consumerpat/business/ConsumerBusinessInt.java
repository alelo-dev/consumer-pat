package br.com.alelo.consumer.consumerpat.business;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerBusinessInt {

	Consumer createConsumer(Consumer consumer) throws Exception;

	Consumer updateConsumer(Consumer consumer) throws Exception;

	void addBalance(long cardNumber, double value) throws Exception;

	Consumer buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value)
			throws Exception;

	List<Consumer> listAllConsumers();

}