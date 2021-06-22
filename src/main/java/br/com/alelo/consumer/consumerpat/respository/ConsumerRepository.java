package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.data.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query("SELECT c FROM Consumer c")
    List<Consumer> getAllConsumersList();

}
