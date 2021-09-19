package br.com.alelo.consumer.consumerpat.infrastructure.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.domain.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

	Optional<Consumer> findByDocumentNumber(String documentNumber);
}
