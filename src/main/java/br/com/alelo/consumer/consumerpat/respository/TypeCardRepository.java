package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.TypeCard;

@Repository
public interface TypeCardRepository extends JpaRepository<TypeCard, String> {
	
	Optional<TypeCard> findByTypeCard(String typeCard);
	
}
