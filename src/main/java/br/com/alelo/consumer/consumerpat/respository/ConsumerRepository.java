package br.com.alelo.consumer.consumerpat.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Consumer SET FOOD_CARD_BALANCE = :newBalance WHERE FOOD_CARD_NUMBER = :cardNumber")
    void updateFoodCardBalanceByCardNumber(int cardNumber, double newBalance);
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE Consumer SET FUEL_CARD_BALANCE = :newBalance WHERE FUEL_CARD_NUMBER = :cardNumber")
    void updateFuelCardBalanceByCardNumber(int cardNumber, double newBalance);
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE CONSUMER SET DRUGSTORE_CARD_BALANCE = :newBalance WHERE DRUGSTORE_CARD_NUMBER = :cardNumber")
    void updateDrugstoreCardBalanceByCardNumber(int cardNumber, double newBalance);
	
	@Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = :cardNumber OR FUEL_CARD_NUMBER  = :cardNumber OR DRUGSTORE_CARD_NUMBER = :cardNumber")
    Optional<Consumer> findByCardNumber(int cardNumber);
	
    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
    Optional<Consumer> findByFoodCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
    Optional<Consumer> findByFuelCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
    Optional<Consumer> findByDrugstoreNumber(int cardNumber);
}
