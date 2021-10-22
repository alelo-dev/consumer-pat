package br.com.alelo.consumer.consumerpat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.domain.model.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
}
