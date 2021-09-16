package br.com.alelo.consumer.consumerpat.data.repository;

import br.com.alelo.consumer.consumerpat.data.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, String> {
    List<Card> findByConsumerId(Integer consumerId);
}
