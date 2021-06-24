package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Consumer findByFoodCardNumber(int cardNumber);

    Consumer findByFuelCardNumber(int cardNumber);

    Consumer findByDrugstoreNumber(int cardNumber);
}
