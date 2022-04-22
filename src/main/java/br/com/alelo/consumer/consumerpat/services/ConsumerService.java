package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ConsumerService {

    Consumer save(Consumer consumer);

    Consumer update(Long id, Consumer consumer);

    Consumer findOrFail(Long id);

    Page<Consumer> findAll(Specification<Consumer> filter, Pageable pageable);
}
