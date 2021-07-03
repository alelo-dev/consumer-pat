package br.com.alelo.consumer.consumerpat.integration.respository;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNumber(Long number);

}
