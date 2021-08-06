package br.com.alelo.consumer.consumerpat.respository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	@Query("SELECT c.balance FROM Card c WHERE c.id = :cardId")
	BigDecimal findBalanceByCardId(Long cardId);

	
	Optional<Card> findByNumber(String cardNumber);
}
