package br.com.alelo.consumer.consumerpat.model.repository;

import br.com.alelo.consumer.consumerpat.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE c.number = :cardNumber")
    Optional<Card> findByCardNumber(Long cardNumber);

}
