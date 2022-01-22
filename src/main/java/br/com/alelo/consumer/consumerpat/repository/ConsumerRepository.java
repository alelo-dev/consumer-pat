package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM Consumer WHERE DOCUMENT_NUMBER = ? ")
    Optional<Consumer> findByDocumentNumber(String documentNumber);
}
