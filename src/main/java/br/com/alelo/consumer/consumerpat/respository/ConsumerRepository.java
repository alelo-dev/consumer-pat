package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

  @Query(value = "select a from Consumer a where a.drugstoreNumber = :number or a.foodCardNumber = :number or a.fuelCardNumber = :number")
  Optional<Consumer> findByCardNumber(int number);
}
