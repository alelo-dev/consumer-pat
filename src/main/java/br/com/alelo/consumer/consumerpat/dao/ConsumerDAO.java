package br.com.alelo.consumer.consumerpat.dao;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerCardDTO;
import br.com.alelo.consumer.consumerpat.entity.ConsumerDTO;

public interface ConsumerDAO {
	
	public List<Consumer> listAllConsumersDao();
	public void createConsumeDao(Consumer consumer);
	public void updateConsumerDao(ConsumerDTO consumerDTO);
	public void setBalanceDao(ConsumerCardDTO consumerCardDTO);
	public void buyDao(ConsumerCardDTO consumerCardDTO);
	
}
