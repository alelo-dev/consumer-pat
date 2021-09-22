package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    @Query(nativeQuery = true, value = "select * from Consumer where food_card_number = :cardNumber or fuel_card_number = :cardNumber or drugstore_card_number = :cardNumber ")
    Consumer  findConsumerByCard(@Param("cardNumber") Long cardNumber);

    @Query(nativeQuery = true, value = "select * from Consumer where food_card_number = :foodCardNumber " +
            "or fuel_card_number = :fuelCardNumber or drugstore_card_number = :drugstoreCardNumber ")
    List<Consumer> findConsumerByCardType(@Param("foodCardNumber") Long foodCardNumber, @Param("fuelCardNumber") Long fuelCardNumber, @Param("drugstoreCardNumber") Long drugstoreCardNumber);
}
