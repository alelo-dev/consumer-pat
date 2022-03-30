package br.com.alelo.consumer.consumerpat.service.interfaces;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface ConsumerService {
    Consumer insert(ConsumerDTO consumer);

    Consumer update(ConsumerDTO consumer);

    List<ConsumerDTO> getAllConsumersList();

    void buy(BuyDTO buy);
}
