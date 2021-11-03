package br.com.alelo.consumer.consumerpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.model.Extract;

public interface ExtractRepository extends JpaRepository<Extract, Long> {
}
