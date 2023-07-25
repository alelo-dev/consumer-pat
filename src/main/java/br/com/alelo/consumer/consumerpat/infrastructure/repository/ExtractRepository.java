package br.com.alelo.consumer.consumerpat.infrastructure.repository;

import br.com.alelo.consumer.consumerpat.infrastructure.repository.entity.Extract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {
}
