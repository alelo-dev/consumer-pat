package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
    Consumer findByFoodCardNumber(Long cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
    Consumer findByFuelCardNumber(Long cardNumber);

    @Query(nativeQuery = true, value = "SELECT * FROM CONSUMER WHERE DRUGSTORE_NUMBER = ?")
    Consumer findByDrugstoreNumber(Long cardNumber);


}
