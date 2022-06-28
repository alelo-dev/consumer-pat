package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
  @Query(
    nativeQuery = true,
    name = "CONSUMER_BY_FOOD_CARD_NUMBER",
    value = "select consumer from Consumer where FOOD_CARD_NUMBER = :cardNumber "
  )
  Consumer findByFoodCardNumber(int cardNumber);

  @Query(
    nativeQuery = true,
    name = "CONSUMER_BY_FUEL_CARD_NUMBER",
    value = "select consumer from Consumer where FUEL_CARD_NUMBER = :cardNumber "
  )
  Consumer findByFuelCardNumber(int cardNumber);

  @Query(
    nativeQuery = true,
    name = "CONSUMER_BY_DRUGSTORE_NUMBER",
    value = "select consumer from Consumer where DRUGSTORE_NUMBER = :cardNumber "
  )
  Consumer findByDrugstoreNumber(int cardNumber);
}
