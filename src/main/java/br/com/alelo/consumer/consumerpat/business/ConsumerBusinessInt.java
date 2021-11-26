package br.com.alelo.consumer.consumerpat.business;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;

public interface ConsumerBusinessInt {

	Consumer createConsumer(Consumer consumer) throws BusinessException;

	Consumer updateConsumer(Consumer consumer) throws BusinessException;

	void addBalance(long cardNumber, double value) throws BusinessException;

	void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value)
			throws BusinessException;

	List<Consumer> listAllConsumers();

}