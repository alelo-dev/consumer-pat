package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.domain.enums.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardsRepository extends JpaRepository<Cards, Long> {

    @Query(nativeQuery = true, value = "select * from Cards where FOOD_CARD_NUMBER = cardType")
    Cards findByFoodCardNumber(@Param("cardType") int cardNumber);

    @Query(nativeQuery = true, value = "select * from Cards where FUEL_CARD_NUMBER = cardType ")
    Cards findByFuelCardNumber(@Param("cardType") int cardNumber);

    @Query(nativeQuery = true, value = "select * from Cards where DRUGSTORE_NUMBER = cardType ")
    Cards findByDrugstoreNumber(@Param("cardType") int cardNumber);

}
