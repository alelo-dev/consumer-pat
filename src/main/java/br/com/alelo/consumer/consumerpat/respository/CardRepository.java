package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.card.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
	
    Card findByNumberAndCategoryId(String number, Integer category);
}
