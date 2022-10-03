package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM Card WHERE CARD_NUMBER = ?")
	Optional<Card> findCardByNumber(String number);

	@Query(nativeQuery = true, value = "SELECT * FROM Card WHERE ID_CONSUMER = ?1")
	List<Card> getAllConsumersCards(Integer idConsumer);

}
