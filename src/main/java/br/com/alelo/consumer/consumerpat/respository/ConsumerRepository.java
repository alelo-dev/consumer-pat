package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerRepository extends CrudRepository<Consumer, Integer> {

    List<Consumer> findAll();

    Consumer findByCardFoodCardNumber(int cardNumber);

    Consumer findByCardFuelCardNumber(int cardNumber);

    Consumer findByCardDrugstoreNumber(int cardNumber);

}
