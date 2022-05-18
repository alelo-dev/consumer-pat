package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Consumer findByFoodCardNumber(int cardNumber);

    /// @Query(nativeQuery = true, value = "select * from Consumer where
    /// FUEL_CARD_NUMBER = ? ")
    Consumer findByFuelCardNumber(int cardNumber);

    // Query(nativeQuery = true, value = "select * from Consumer where
    // DRUGSTORE_NUMBER = ? ")
    Consumer findByDrugstoreNumber(int cardNumber);
}
