package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alelo.consumer.consumerpat.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

    @Query("select card from Card card where card.number = :cardNumber " )
    Card findByCardNumber(//
    		@Param("cardNumber") Long cardNumber);

}
