package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, UUID> {
	
	Optional<Consumer> findByDocumentNumber(String documentNumber);

}
