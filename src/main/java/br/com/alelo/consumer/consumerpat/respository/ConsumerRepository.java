package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Integer> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    List<ConsumerEntity> getAllConsumersList();

    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
    ConsumerEntity findByFoodCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
    ConsumerEntity findByFuelCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
    ConsumerEntity findByDrugstoreNumber(int cardNumber);
}
