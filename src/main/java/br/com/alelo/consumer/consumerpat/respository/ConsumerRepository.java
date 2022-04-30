package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    @Query(" select c FROM Consumer c WHERE (c.foodCardNumber=:cardNumber OR c.fuelCardNumber = :cardNumber OR c.drugstoreNumber = :cardNumber)")
    Optional<Consumer> findOneByDrugstoreNumberOrFoodCardNumberOrFuelCardNumber(Long cardNumber);

    boolean existsByDocumentNumber(Long documentNumber);
    boolean existsByFoodCardNumber(Long foodCardNumber);
    boolean existsByFuelCardNumber(Long fuelCardNumber);
    boolean existsByDrugstoreNumber(Long drugstoreNumber);

}
