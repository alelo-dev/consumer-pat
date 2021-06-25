package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.data.model.Card;
import br.com.alelo.consumer.consumerpat.data.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query("SELECT c FROM Card c where c.number = :cardNumber and c.type = :typeCard")
    Optional<Card> findByFoodCardNumberAndType(@Param("cardNumber") Long cardNumber, @Param("typeCard") int typeCard);

    @Query("SELECT c FROM Card c where c.number = :cardNumber")
    Optional<Card> findByCardNumber(@Param("cardNumber") Long cardNumber);
}
