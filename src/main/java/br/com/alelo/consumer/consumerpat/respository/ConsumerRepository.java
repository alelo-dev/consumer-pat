package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
  @Query(nativeQuery = true, value = "select * from Consumer")
  Page<Consumer> findAllConsumers(Pageable pageable);

  Optional<Consumer> findByDocumentNumber(String documentNumber);
}
