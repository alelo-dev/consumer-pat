package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.contants.CardType;
import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {

    Optional<Card> getCardByCardNumberAndCardType(String CardNumber, CardType cardType);
}
