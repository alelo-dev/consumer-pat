package br.com.alelo.consumer.consumerpat.infrastructure.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.domain.Extract;

public interface ExtractRepository extends JpaRepository<Extract, Long> {
}
