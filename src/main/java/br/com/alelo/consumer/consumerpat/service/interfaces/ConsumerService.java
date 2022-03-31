package br.com.alelo.consumer.consumerpat.service.interfaces;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;

import java.util.List;

public interface ConsumerService {
    ConsumerDTO insert(ConsumerDTO consumer);

    ConsumerDTO update(ConsumerDTO consumer);

    List<ConsumerDTO> getAllConsumersList();
}
