package br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer;

import br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer.entity.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsumerJpaRepository extends JpaRepository<ConsumerEntity, UUID> {

}
