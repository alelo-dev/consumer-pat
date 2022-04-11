package br.com.alelo.consumer.consumerpat.repositories;

import br.com.alelo.consumer.consumerpat.models.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsumerRepository extends JpaRepository<Consumer, UUID> {

}
