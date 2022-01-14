package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

}
