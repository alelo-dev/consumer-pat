package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findByCardTypeAndCardNumber(Integer cardType, long cardNumber);

    Optional<Card> findByCardNumber(long cardNumber);

    List<Card> findByConsumerId(Integer consumerId);

}
