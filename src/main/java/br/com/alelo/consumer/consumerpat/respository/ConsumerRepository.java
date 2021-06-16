package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Optional<Consumer> findByCardsFoodCardNumber(int cardNumber);

    Optional<Consumer> findByCardsFuelCardNumber(int cardNumber);

    Optional<Consumer> findByCardsDrugstoreNumber(int cardNumber);
}
