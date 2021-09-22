package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    List<Consumer> findAll();

    Consumer findByFoodCardNumber(String cardNumber);

    Consumer findByFuelCardNumber(String cardNumber);

    Consumer findByDrugstoreCardNumber(String cardNumber);
}
