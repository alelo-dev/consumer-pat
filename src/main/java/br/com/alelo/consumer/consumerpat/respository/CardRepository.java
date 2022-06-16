package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alelo.consumer.consumerpat.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
	static final String FIND_BY_NUMBER = "select c from Card c where c.number=(?1)";

	@Query(FIND_BY_NUMBER)
	Card findByNumber(int number);

}
