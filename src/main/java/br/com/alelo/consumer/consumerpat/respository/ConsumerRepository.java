package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    List<Consumer> getAllConsumersList();

    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
    Consumer findByFoodCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
    Consumer findByFuelCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
    Consumer findByDrugstoreNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select count(*) > 0 from Consumer where FOOD_CARD_NUMBER = ? or FUEL_CARD_NUMBER = ? or DRUGSTORE_NUMBER = ? ")
	Boolean existsCardNumbers(Integer foodCardNumber, Integer fuelCardNumber, Integer drugstoreNumber);
    
    @Query(nativeQuery = true, value = "select count(*) > 0 from Consumer where ID <> ? and (FOOD_CARD_NUMBER = ? or FUEL_CARD_NUMBER = ? or DRUGSTORE_NUMBER = ?) ")
	Boolean existsCardNumbers(Integer id, Integer foodCardNumber, Integer fuelCardNumber, Integer drugstoreNumber);
}
