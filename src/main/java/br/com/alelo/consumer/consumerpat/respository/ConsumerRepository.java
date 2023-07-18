package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
    @Query("select c from Consumer c")
    List<Consumer> getAllConsumers();

    @Query("select c from Consumer c where c.foodCardNumber = :cardNumber")
    Consumer findByFoodCardNumber(@Param("cardNumber") int cardNumber);

    @Query("select c from Consumer c where c.fuelCardNumber = :cardNumber")
    Consumer findByFuelCardNumber(@Param("cardNumber") int cardNumber);

    @Query("select c from Consumer c where c.drugstoreNumber = :cardNumber")
    Consumer findByDrugstoreNumber(@Param("cardNumber") int cardNumber);

}