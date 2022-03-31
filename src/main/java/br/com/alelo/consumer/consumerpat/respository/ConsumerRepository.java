package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    @Query(nativeQuery = true, value = "select * from Consumer")
    List<Consumer> getAllConsumersList();

    @Query(nativeQuery = true, value = "select * from consumer c " +
            "inner join card ca on c.id = ca.consumer_id where ca.id = :cardNumber ")
    Optional<Consumer> findByCardNumber(int cardNumber);
}
