package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerNotFoundException;

import java.util.List;

public interface ConsumerService {

    ConsumerEntity saveConsumer(ConsumerDTO consumer);
    ConsumerEntity updateConsumer(int idConsumer, ConsumerDTO consumer) throws ConsumerNotFoundException;
    List<ConsumerEntity> getAllConsumers();
    ConsumerEntity getConsumerById(int consumerId) throws ConsumerNotFoundException;
    ConsumerEntity getByCardNumber(int cardNumber);

}
