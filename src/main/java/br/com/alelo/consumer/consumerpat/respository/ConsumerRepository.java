package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alelo.consumer.consumerpat.model.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    
    @Query("select consumer from Consumer consumer join consumer.cards card where card.number = :cardNumber " )
    Consumer findByCardNumber(//
    		@Param("cardNumber") Long cardNumber);
    
}
