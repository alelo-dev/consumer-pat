package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Consumer findByIdAndFoodCardNumber(int id, int cardNumber);

    Consumer findByIdAndFuelCardNumber(int id, int cardNumber);

    Consumer findByIdAndDrugstoreNumber(int id, int cardNumber);
}
