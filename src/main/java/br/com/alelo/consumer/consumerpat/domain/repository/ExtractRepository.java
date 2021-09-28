package br.com.alelo.consumer.consumerpat.domain.repository;

import br.com.alelo.consumer.consumerpat.domain.entity.ExtractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractRepository extends JpaRepository<ExtractEntity, Integer> {
}
