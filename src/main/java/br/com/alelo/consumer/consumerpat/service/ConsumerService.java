package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface ConsumerService {

    List<Consumer> listAllConsumers();
    void createConsumer(ConsumerDTO dto);
    void updateConsumer(ConsumerDTO dto);

}
