package br.com.alelo.consumer.consumerpat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

@Repository
public interface ConsumerRepository extends PagingAndSortingRepository<Consumer, Long> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    List<Consumer> getAllConsumersList();

    @Query("select a from Consumer a WHERE a.documentNumber = :documentConsumer")
    Optional<Consumer> getConsumerByDocument(@Param("documentConsumer") Long documentConsumer);
}
