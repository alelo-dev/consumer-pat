package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;

@Repository
public interface ExtractRepository extends JpaRepository<Extract, UUID> {
	
	List<Extract> findByCard(Card Card);
	
}
