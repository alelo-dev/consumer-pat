package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    List<Consumer> findAll(); // Equivalente ao método original getAllConsumersList()

    Optional<Consumer> findByFoodCardNumber(int cardNumber);

    Optional<Consumer> findByFuelCardNumber(int cardNumber);

    Optional<Consumer> findByDrugstoreNumber(int cardNumber);
}