package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query("from Consumer c")
    List<Consumer> getAllConsumersList();

    @Query("from Consumer c where c.foodCardNumber = :cardNumber")
    Consumer findByFoodCardNumber(@Param("cardNumber") int cardNumber);

    @Query("from Consumer c where c.fuelCardNumber :cardNumber ")
    Consumer findByFuelCardNumber(@Param("cardNumber") int cardNumber);

    @Query("from Consumer c where c.drugstoreNumber :cardNumber")
    Consumer findByDrugstoreNumber(@Param("cardNumber") int cardNumber);
}
