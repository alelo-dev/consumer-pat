package br.com.alelo.consumer.consumerpat.respository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	public Card findByNumber(Integer number);
	
}