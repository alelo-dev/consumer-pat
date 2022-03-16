package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;

public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByTypeAndNumber(CardType type, Long cardNumber);

    List<Card> findByConsumerName(String consumerName);
}
