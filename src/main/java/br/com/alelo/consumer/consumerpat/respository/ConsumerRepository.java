package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    List<Consumer> getAllConsumersList();

    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
    Consumer findByFoodCardNumber(Long cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
    Consumer findByFuelCardNumber(Long cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
    Consumer findByDrugstoreNumber(Long cardNumber);
}
