package br.com.alelo.consumer.consumerpat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByIdAndConsumer(Long id, Consumer consumer);
    
    Optional<Card> findByNumberAndConsumer(String number, Consumer consumer);

}
