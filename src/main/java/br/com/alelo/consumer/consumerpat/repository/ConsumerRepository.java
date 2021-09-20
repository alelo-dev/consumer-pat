package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    List<Consumer> findAll();

    @Query(value = "select c from Consumer c where c.id = :id ")
    Consumer findConsumerById(int id);

    Consumer findByFoodCardNumber(int cardNumber);

    Consumer findByFuelCardNumber(int cardNumber);

    Consumer findByDrugstoreNumber(int cardNumber);
}
