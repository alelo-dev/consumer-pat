package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.entity.ConsumerEntity;

public interface IConsumerEntityRepository extends JpaRepository<ConsumerEntity, Integer> {
    
}
