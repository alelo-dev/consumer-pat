package br.com.alelo.consumer.consumerpat.respositories;

import br.com.alelo.consumer.consumerpat.entities.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query("FROM Consumer WHERE foodCardNumber = :cardNumber OR fuelCardNumber = :cardNumber OR drugstoreCardNumber = :cardNumber")
    Consumer findOneByCardNumber(Integer cardNumber);

}
