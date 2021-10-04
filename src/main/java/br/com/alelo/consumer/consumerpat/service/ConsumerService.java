package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Extract;
public interface ConsumerService {

    ConsumerDTO createConsumer(ConsumerDTO consumerDTO);

    ConsumerDTO updateConsumer(Long id, ConsumerDTO consumerDTO);

    void addValue(int cardNumber, double value);

    Extract debitValue(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value);

}
