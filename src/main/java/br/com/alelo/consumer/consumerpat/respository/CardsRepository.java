package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long>{

	Cards findByFoodCardNumber(int cardNumber);
	
	Cards findByFuelCardNumber(int cardNumber);
	
	Cards findByDrugstoreNumber(int cardNumber);
}
