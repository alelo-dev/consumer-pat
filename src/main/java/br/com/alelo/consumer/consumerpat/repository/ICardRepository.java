package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for to manipulate data of card
 *
 * @author mcrj
 */
public interface ICardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNumber(final Long number);
}
