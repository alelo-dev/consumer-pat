package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for to manipulate data of consumer
 *
 * @author mcrj
 */
public interface IConsumerRepository extends JpaRepository<Consumer, Long> { }
