package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.enums.TypeCard;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

	Optional<Card> findByCardNumber(Integer cardNumber);
	
	Optional<Card> findByCardNumberAndTypeCardAndBalanceGreaterThanEqual (Integer cardNumber, TypeCard typeCard, Double balance);
}
