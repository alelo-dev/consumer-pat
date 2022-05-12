package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    @Query(nativeQuery = true, value = "select * from Consumer where DOCUMENT_NUMBER = ?")
    Optional<Consumer> findConsumerByDocumentNumber(final String documentNumber);
}
