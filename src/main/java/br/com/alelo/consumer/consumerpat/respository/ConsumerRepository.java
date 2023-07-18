package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
    @Query("select c from Consumer c")
    List<Consumer> getAllConsumers();

    @Query("select c from Consumer c where c.foodCardNumber = :cardNumber")
    Optional<Consumer> findByFoodCardNumber(@Param("cardNumber") int cardNumber);

    @Query("select c from Consumer c where c.fuelCardNumber = :cardNumber")
    Optional<Consumer> findByFuelCardNumber(@Param("cardNumber") int cardNumber);

    @Query("select c from Consumer c where c.drugstoreNumber = :cardNumber")
    Optional<Consumer> findByDrugstoreNumber(@Param("cardNumber") int cardNumber);
}