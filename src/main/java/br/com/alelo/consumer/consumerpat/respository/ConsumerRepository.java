package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    List<Consumer> getAllConsumersList();

    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
    Consumer findByFoodCardNumber(long cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
    Consumer findByFuelCardNumber(long cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_CARD_NUMBER = ? ")
    Consumer findByDrugstoreNumber(long cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_CARD_NUMBER = ?1 OR FUEL_CARD_NUMBER = ?1 OR FOOD_CARD_NUMBER = ?1")
    Consumer findCardNumberAllTypes(long cardNumber);
}
