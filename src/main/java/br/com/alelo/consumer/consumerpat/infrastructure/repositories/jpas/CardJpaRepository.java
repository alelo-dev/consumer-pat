package br.com.alelo.consumer.consumerpat.infrastructure.repositories.jpas;

import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardJpaRepository extends JpaRepository<CardEntity, Integer> {

    @Query(
            nativeQuery = true,
            value = "select * from card c where c.card_number = :cardNumber and c.consumer_id = :consumerId"
    )
    Optional<CardEntity> findByCardNumberAndConsumerId(
            @Param("cardNumber") String cardNumber,
            @Param("consumerId") int consumerId
    );

}
