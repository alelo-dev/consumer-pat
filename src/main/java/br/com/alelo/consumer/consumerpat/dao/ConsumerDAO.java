package br.com.alelo.consumer.consumerpat.dao;

import br.com.alelo.consumer.consumerpat.DTO.ConsumerCardDTO;
import br.com.alelo.consumer.consumerpat.DTO.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface ConsumerDAO {

    public List<Consumer> listAllConsumersDao();
    public void createConsumeDao(Consumer consumer);
    public void updateConsumerDao(Consumer consumer);

}
