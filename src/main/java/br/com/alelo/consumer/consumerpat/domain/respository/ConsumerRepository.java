package br.com.alelo.consumer.consumerpat.domain.respository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

//    @Query(nativeQuery = true, value = "select * from Consumer")
//    List<Consumer> getAllConsumersList();
//
//    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
//    Consumer findByFoodCardNumber(int cardNumber);
//
//    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
//    Consumer findByFuelCardNumber(int cardNumber);
//
//    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
//    Consumer findByDrugstoreNumber(int cardNumber);
}
