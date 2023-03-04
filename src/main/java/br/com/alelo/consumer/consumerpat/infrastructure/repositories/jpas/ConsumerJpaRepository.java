package br.com.alelo.consumer.consumerpat.infrastructure.repositories.jpas;

import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.ConsumerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerJpaRepository extends JpaRepository<ConsumerEntity, Integer> {

    @Query("From ConsumerEntity c")
    Page<ConsumerEntity> findAllPageable(Pageable pageable);

}
