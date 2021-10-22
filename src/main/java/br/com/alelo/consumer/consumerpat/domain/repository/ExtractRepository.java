package br.com.alelo.consumer.consumerpat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.domain.model.Extract;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {
}
