package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.domain.consumer.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    Page<Consumer> getAllConsumersList(final Pageable pageable);

//    @Query(nativeQuery = true, value = "select * from Consumer where FOOD_CARD_NUMBER = ? ")
//    Optional<Consumer> findByFoodCardNumber(int cardNumber);
//
//    @Query(nativeQuery = true, value = "select * from Consumer where FUEL_CARD_NUMBER = ? ")
//    Optional<Consumer> findByFuelCardNumber(int cardNumber);
//
//    @Query(nativeQuery = true, value = "select * from Consumer where DRUGSTORE_NUMBER = ? ")
//    Optional<Consumer> findByDrugstoreNumber(int cardNumber);
}
