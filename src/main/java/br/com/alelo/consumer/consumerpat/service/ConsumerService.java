package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.ConsumerCardDTO;
import br.com.alelo.consumer.consumerpat.entity.ConsumerDTO;

public interface ConsumerService {
	
	public List<Consumer> listAllConsumersService();
	public void createConsumeService(Consumer consumer);
	public void updateConsumerService(ConsumerDTO consumerDTO);
	public void setBalanceService(ConsumerCardDTO consumerCardDTO);
	public void buyService(ConsumerCardDTO consumerCardDTO);
}
