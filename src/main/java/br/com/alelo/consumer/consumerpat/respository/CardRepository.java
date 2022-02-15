package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
	
	Optional<Card> getCardByNumber(String cardNumber);
}
