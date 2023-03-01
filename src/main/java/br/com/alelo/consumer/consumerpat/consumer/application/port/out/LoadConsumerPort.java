package br.com.alelo.consumer.consumerpat.consumer.application.port.out;

import br.com.alelo.consumer.consumerpat.consumer.domain.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoadConsumerPort {

    Optional<Consumer> findByConsumerId(Integer consumerId);

    Page<Consumer> findAll(Pageable pageable);
}
