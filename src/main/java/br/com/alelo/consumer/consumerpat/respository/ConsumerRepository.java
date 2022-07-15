package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ConsumerRepository extends PagingAndSortingRepository<Consumer, Long> {

    @Query(value = "SELECT c FROM Consumer c")
    List<Consumer> getAllConsumersList();

    @Query(value = "SELECT c FROM Consumer c WHERE c.foodCardNumber = ?1")
    Consumer findByFoodCardNumber(int cardNumber);

    @Query(value = "SELECT c FROM Consumer c WHERE c.fuelCardNumber = ?1")
    Consumer findByFuelCardNumber(int cardNumber);

    @Query(value = "SELECT c FROM Consumer c WHERE c.drugstoreCardNumber = ?1")
    Consumer findByDrugstoreCardNumber(int cardNumber);

}
