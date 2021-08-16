package br.com.alelo.consumer.consumerpat.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.consumer.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
   
}
