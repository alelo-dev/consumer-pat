package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Consumer findByFoodCardNumber(Long cardNumber);

    Consumer findByFuelCardNumber(Long cardNumber);

    Consumer findByDrugstoreNumber(Long cardNumber);
}
