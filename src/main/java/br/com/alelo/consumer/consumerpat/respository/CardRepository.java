package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.repository.CrudRepository;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;

public interface CardRepository extends CrudRepository<Card, String> {

}
