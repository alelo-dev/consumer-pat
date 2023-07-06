package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query(nativeQuery = true, value = "select * from Card where FOOD_CARD_NUMBER = ? ")
    Card findByFoodCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Card where FUEL_CARD_NUMBER = ? ")
    Card findByFuelCardNumber(int cardNumber);

    @Query(nativeQuery = true, value = "select * from Card where DRUGSTORE_CARD_NUMBER = ? ")
    Card findByDrugstoreCardNumber(int cardNumber);

}
