package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Responsible for securing consumer service subscriptions
 *
 * @author mcrj
 */
public interface IConsumerService {

    /**
     * Create new consumer
     *
     * @param consumer - {@link Consumer}
     * @return {@link Consumer} - Created consumer
     */
    Consumer save(final Consumer consumer);

    /**
     * Update the consumer
     *
     * @param consumer - {@link Consumer}
     * @return {@link Consumer} - Created consumer
     */
    Consumer update(final Consumer consumer);

    /**
     * Find all consumers
     *
     * @param pageable - {@link Pageable} - Query paging
     * @return {@link Page<Consumer>}
     */
    Page<Consumer> findAll(final Pageable pageable);
}
