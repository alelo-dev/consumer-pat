package br.com.alelo.consumer.consumerpat.model.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
