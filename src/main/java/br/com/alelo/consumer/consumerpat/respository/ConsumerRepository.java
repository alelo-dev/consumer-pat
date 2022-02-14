package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query("select c from Consumer c where foodCardNumber = :cardNumber ")
    Consumer findByFoodCardNumber(int cardNumber);

    @Query("select c from Consumer c where fuelCardNumber = :cardNumber ")
    Consumer findByFuelCardNumber(int cardNumber);

    @Query("select c from Consumer c where drugstoreNumber = :cardNumber ")
    Consumer findByDrugstoreNumber(int cardNumber);
}
