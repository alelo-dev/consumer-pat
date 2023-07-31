package br.com.alelo.consumer.consumerpat.infrastructure.repository.card;

import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CardJpaRepository extends JpaRepository<CardEntity, String> {

    Optional<CardEntity> findByCardNumber(String cardNumber);

    @Query("SELECT c FROM CardEntity c WHERE c.consumer.id = :consumerId")
    Optional<Set<CardEntity>> findAllByConsumerId(UUID consumerId);

}
