package br.com.alelo.consumer.consumerpat.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.domain.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNumber(int cardNumber);
}
