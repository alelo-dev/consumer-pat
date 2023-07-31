package br.com.alelo.consumer.consumerpat.infrastructure.repository.card;

import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity.CardBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardBalanceJpaRepository extends JpaRepository<CardBalanceEntity, UUID> {

    Optional<CardBalanceEntity> findByCardNumber(String cardNumber);
}
