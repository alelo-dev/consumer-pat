package br.com.alelo.consumer.consumerpat.business;

import java.util.List;
import java.util.Optional;
import br.com.alelo.consumer.consumerpat.dto.v2.ConsumerDTO;

public interface IConsumerBusiness {
     
    Optional<List<ConsumerDTO>> listAllConsumers();

}
