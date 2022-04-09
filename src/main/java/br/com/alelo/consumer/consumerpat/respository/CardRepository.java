package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.TypeCard;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
	
    Optional<Card> findByCardNumber(Long cardNumber);
    
    Optional<Card> findByCardNumberAndTypeCard(Long cardNumber, TypeCard typeCard);
	
}
