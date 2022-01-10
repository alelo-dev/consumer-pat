package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.TypeCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeCardRepository extends JpaRepository<TypeCard, Long> {
    Optional<TypeCard> findByTypeCard(String typeCard);
}
