package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumerService {

    Consumer createConsumer(final Consumer consumer);

    Consumer updateConsumer(final Integer id, final Consumer consumer);

    Page<Consumer> getPageConsumer(final Pageable pageable);

}
