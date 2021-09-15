package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ?1 OR FUEL_CARD_NUMBER = ?1 OR DRUG_STORE_NUMBER = ?1 ")
    Optional<Consumer> findByCardNumber(String cardNumber);

    Optional<Consumer> findByFoodCardNumber(String cardNumber);

    Optional<Consumer> findByFuelCardNumber(String cardNumber);

    Optional<Consumer> findByDrugStoreNumber(String cardNumber);
}
