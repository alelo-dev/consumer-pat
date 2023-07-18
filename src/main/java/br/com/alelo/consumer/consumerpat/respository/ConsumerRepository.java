package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
    Optional<Consumer> findByFoodCardNumber(@Param("cardNumber") int cardNumber);

    Optional<Consumer> findByFuelCardNumber(@Param("cardNumber") int cardNumber);

    Optional<Consumer> findByDrugstoreCardNumber(@Param("cardNumber") int cardNumber);
}