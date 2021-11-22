package br.com.alelo.consumer.consumerpat.respository.jpa;
  
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerJPA extends JpaRepository<Consumer, Integer> {
    
	 @Query(nativeQuery = true, value = "select * from Consumer")
	 List<Consumer> getAllConsumersList();

	 @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
	 Consumer findByFoodCardNumber(int cardNumber);

	 @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
	 Consumer findByFuelCardNumber(int cardNumber);

	 @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
	 Consumer findByDrugstoreNumber(int cardNumber);	

}
