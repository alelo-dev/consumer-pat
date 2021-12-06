package br.com.alelo.consumer.consumerpat.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity.ConsumerEntity;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Integer> {

}
