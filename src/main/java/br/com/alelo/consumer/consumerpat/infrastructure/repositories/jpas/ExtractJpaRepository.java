package br.com.alelo.consumer.consumerpat.infrastructure.repositories.jpas;

import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.ExtractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractJpaRepository extends JpaRepository<ExtractEntity, Integer> {

    @Query("From ExtractEntity e where e.consumerId = :consumerId")
    Page<ExtractEntity> findAllPageable(@Param("consumerId") int consumerId, Pageable pageable);
}
