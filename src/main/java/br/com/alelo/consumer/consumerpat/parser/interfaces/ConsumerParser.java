package br.com.alelo.consumer.consumerpat.parser.interfaces;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.List;

public interface ConsumerParser {
    Consumer parse(ConsumerDTO consumerDTO);
    List<ConsumerDTO> parse(List<Consumer> consumerList);
}
