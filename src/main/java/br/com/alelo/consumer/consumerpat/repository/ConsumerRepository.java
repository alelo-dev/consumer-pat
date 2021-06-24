package br.com.alelo.consumer.consumerpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
	
	boolean existsByDocumentNumber(String documentNumber);

}