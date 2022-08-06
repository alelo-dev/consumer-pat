package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    Optional<Consumer> findByDocumentNumber(String documentNumber);

    @Query(value = "SELECT EXISTS ( " +
            "SELECT C.DOCUMENT_NUMBER  FROM CONSUMER C " +
            "where C.DOCUMENT_NUMBER = :documentNumber )"
            , nativeQuery = true)
    boolean existsDocument(String documentNumber);
}
