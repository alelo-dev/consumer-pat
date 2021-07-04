package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.domain.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {


    List<Card> findByConsumerId(final long consumerId);
    @Query(value = "select * from cards where consumer_id = :consumerId and number_card = :numberCard", nativeQuery = true)
    Optional<Card> findByConsumerAndNumberCard(final long consumerId, final String numberCard);
}
