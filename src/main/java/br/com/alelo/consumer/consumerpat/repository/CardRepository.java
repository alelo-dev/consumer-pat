package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Integer> {

    /**
     * @param idConsumer - Id do cliente
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM Card WHERE ID_CONSUMER = ?1")
    List<Card> getAllConsumersCards(Integer idConsumer);

    /**
     * @param number - Número do cartão
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM Card WHERE number = ?")
    Optional<Card> findCardByNumber(String number);
}
