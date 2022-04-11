package br.com.alelo.consumer.consumerpat.repositories;

import br.com.alelo.consumer.consumerpat.models.Extract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExtractRepository extends JpaRepository<Extract, UUID> {
}
