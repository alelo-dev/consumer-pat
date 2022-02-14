package br.com.alelo.consumer.consumerpat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.consumer.consumerpat.entity.Extract;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {
}
