package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConsumerService {

    Consumer save(final Consumer consumer);

    Consumer update(final Consumer consumer);

    List<Consumer> listAllConsumers(final Pageable pageable);
}
