package br.com.alelo.consumer.consumerpat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;

public interface ConsumerService {

    public Page<ConsumerDTO> listAllConsumers(Pageable pageable);
    public ResultStatus createConsumer(ConsumerDTO consumerDTO);
    public ResultStatus updateConsumer(ConsumerDTO consumerDTO);
    public ResultStatus addCard(Long idConsumer, CardDTO cardDTO);
    
}
