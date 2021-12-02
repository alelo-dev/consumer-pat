package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumerRepository extends CrudRepository<Consumer, Integer> {

    List<Consumer> findAll();

    Optional<Consumer> findByCardFoodCardNumber(int cardNumber);

    Optional<Consumer> findByCardFuelCardNumber(int cardNumber);

    Optional<Consumer> findByCardDrugstoreCardNumber(int cardNumber);
}
