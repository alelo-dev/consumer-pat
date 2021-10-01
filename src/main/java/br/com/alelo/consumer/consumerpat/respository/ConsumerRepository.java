package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    List<Consumer> findAll();

    Optional<Consumer> findByFoodCardNumber(int cardNumber);

    Optional<Consumer> findByFuelCardNumber(int cardNumber);

    Optional<Consumer> findByDrugstoreNumber(int cardNumber);
    
}
