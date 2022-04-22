package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query("select c from Consumer c")
    List<Consumer> getAllConsumersList();

    @Query("select c from Consumer c where foodCardNumber = :cardNumber ")
    Consumer findByFoodCardNumber(@Param("cardNumber") int cardNumber);
   
    @Query("select c from Consumer c where fuelCardNumber = :cardNumber ")
    Consumer findByFuelCardNumber(@Param("cardNumber") int cardNumber);

    @Query("select c from Consumer c where drugstoreNumber = :cardNumber ")
    Consumer findByDrugstoreNumber(@Param("cardNumber") int cardNumber);
}
