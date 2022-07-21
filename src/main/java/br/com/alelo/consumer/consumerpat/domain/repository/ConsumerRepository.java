package br.com.alelo.consumer.consumerpat.domain.repository;

import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ConsumerRepository extends PagingAndSortingRepository<Consumer, BigInteger> {

    Consumer findByFoodCardNumber(BigInteger cardNumber);

    Consumer findByFuelCardNumber(BigInteger cardNumber);

    // OBSERVAÇÃO: apenas para demonstrar o uso do recurso de query personalizada.
    @Query(value = "SELECT c FROM Consumer c WHERE c.drugstoreCardNumber = ?1")
    Consumer findByDrugstoreCardNumber(BigInteger cardNumber);

    Consumer findByDrugstoreCardNumberOrFoodCardNumberOrFuelCardNumber(
            BigInteger drugstoreCardNumber,
            BigInteger foodCardNumber,
            BigInteger fueldCardNumber);

    default Optional<Consumer> findByCardNumber(BigInteger cardNumber, CardType cardType) {

        Consumer consumer = null;

        if (CardType.DRUGSTORE_CARD.equals(cardType)) {
            consumer = this.findByDrugstoreCardNumber(cardNumber);
        }

        if (CardType.FOOD_CARD.equals(cardType)) {
            consumer = this.findByFoodCardNumber(cardNumber);
        }

        if (CardType.FUEL_CARD.equals(cardType)) {
            consumer = this.findByFuelCardNumber(cardNumber);
        }

        return Optional.ofNullable(consumer);
    }

}
