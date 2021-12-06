package br.com.alelo.consumer.consumerpat.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alelo.consumer.consumerpat.adapter.out.persistence.entity.ExtractEntity;

public interface ExtractRepository extends JpaRepository<ExtractEntity, Integer> {
}
