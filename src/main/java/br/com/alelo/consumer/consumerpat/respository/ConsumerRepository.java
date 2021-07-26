package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

//    @Query(nativeQuery = true, value = "select * from consumer where FOOD_CARD_NUMBER = :cardNumber ")
//    Optional<Consumer> findByFoodCardNumber(@Param("cardNumber") Integer cardNumber);
//
//    Optional<Consumer> findByFuelCardNumber(int cardNumber);
//
//    Optional<Consumer> findByDrugstoreNumber(int cardNumber);

    Optional<Consumer> findByDocumentNumber(String number);
}
